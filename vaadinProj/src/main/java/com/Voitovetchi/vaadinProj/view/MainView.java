package com.Voitovetchi.vaadinProj.view;

import com.Voitovetchi.vaadinProj.components.BookEditor;
import com.Voitovetchi.vaadinProj.domain.Book;
import com.Voitovetchi.vaadinProj.repository.BookRepo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import lombok.Data;
import lombok.Getter;

@Route
@Getter
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
        //filter.addValueChangeListener(e -> showBook(e.getValue()));

        /*grid.asSingleSelect().addValueChangeListener(e -> {
            bookEditor.editBook(e.getValue());
        });*/

        //addNewBtn.addClickListener(e -> bookEditor.editBook(new Book()));

        bookEditor.setChangeHandler(() -> {
            bookEditor.setVisible(false);
            showBook(filter.getValue());
        });

        showBook("");
    }


}
