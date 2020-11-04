package com.Voitovetchi.vaadinProj.view;

import com.Voitovetchi.vaadinProj.view.components.BookEditor;
import com.Voitovetchi.vaadinProj.model.domain.Book;
import com.Voitovetchi.vaadinProj.presenter.Presenter;
import com.Voitovetchi.vaadinProj.model.repository.BookRepo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import lombok.Getter;

/**
 * Class that represents the whole UI.
 *
 * @author Iurii Voitovetchi.
 */
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

        Presenter presenter = new Presenter(this, bookRepo);

        presenter.showBook("");
    }

}
