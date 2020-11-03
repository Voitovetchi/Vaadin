package com.Voitovetchi.vaadinProj.presenter;

import com.Voitovetchi.vaadinProj.domain.Book;
import com.Voitovetchi.vaadinProj.repository.BookRepo;
import com.Voitovetchi.vaadinProj.view.MainView;
import com.vaadin.flow.data.binder.Binder;

public class Presenter {
    private Binder<Book> binder = new Binder<>(Book.class);

    private final MainView mainView;
    private final BookRepo bookRepo;

    private Book book = new Book();

    Presenter(MainView mainView, BookRepo bookRepo) {
        this.mainView = mainView;
        this.bookRepo = bookRepo;

        mainView.getFilter().addValueChangeListener(e -> showBook(e.getValue()));

        mainView.getGrid().asSingleSelect().addValueChangeListener(e -> {
            editBook(e.getValue());
        });

        mainView.getAddNewBtn().addClickListener(e -> editBook(new Book()));

        mainView.getBookEditor().setChangeHandler(() -> {
            mainView.getBookEditor().setVisible(false);
            showBook(mainView.getFilter().getValue());
        });
    }

    public void showBook(String title) {
        if (title.isEmpty()) {
            mainView.getGrid().setItems(bookRepo.findAll());
        } else {
            mainView.getGrid().setItems(bookRepo.findByTitle(title));
        }
    }

    public void editBook(Book newBook) {
        if (newBook == null) {
            mainView.getBookEditor().setVisible(false);
            return;
        }

        if (newBook.getIsbn() != null) {
            book = bookRepo.findById(newBook.getIsbn()).orElse(newBook);
        } else {
            book = newBook;
        }

        binder.setBean(book);

        mainView.getBookEditor().setVisible(true);

        mainView.getBookEditor().getTitle().focus();
    }
}
