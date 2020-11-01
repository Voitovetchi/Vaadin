package com.Voitovetchi.vaadinProj.view;

import com.Voitovetchi.vaadinProj.components.BookEditor;
import com.Voitovetchi.vaadinProj.domain.Book;
import com.Voitovetchi.vaadinProj.repository.BookRepo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {
    private final BookRepo bookRepo;

    private Grid<Book> grid = new Grid<>(Book.class);

    private final TextField filter = new TextField("", "Type to filter");
    private final Button addNewBtn = new Button("add new");
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewBtn);

    private final BookEditor bookEditor;

    public MainView(BookRepo bookRepo, BookEditor bookEditor) {
        this.bookRepo = bookRepo;
        this.bookEditor = bookEditor;

        add(toolbar, grid, bookEditor);

        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> showBook(e.getValue()));

        // Connect selected Customer to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> {
            bookEditor.editBook(e.getValue());
        });

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> bookEditor.editBook(new Book()));

        // Listen changes made by the editor, refresh data from backend
        bookEditor.setChangeHandler(() -> {
            bookEditor.setVisible(false);
            showBook(filter.getValue());
        });

        showBook("");
    }

    private void showBook(String title) {
        if (title.isEmpty()) {
            grid.setItems(bookRepo.findAll());
        } else {
            grid.setItems(bookRepo.findByTitle(title));
        }
    }
}
