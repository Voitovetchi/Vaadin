package com.Voitovetchi.vaadinProj.presenter;

import com.Voitovetchi.vaadinProj.model.domain.Book;
import com.Voitovetchi.vaadinProj.model.repository.BookRepo;
import com.Voitovetchi.vaadinProj.presenter.services.Validator;
import com.Voitovetchi.vaadinProj.view.MainView;
import com.vaadin.flow.data.binder.Binder;

/**
 * Class for presenter layer. Sets all listeners in its constructor.
 *
 * @author IuriiVoitovetchi.
 */
public class Presenter {

    private Binder<Book> binder = new Binder<>(Book.class);

    private final MainView mainView;
    private final BookRepo bookRepo;

    private Book book = new Book();

    public Presenter(MainView mainView, BookRepo bookRepo) {
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

        binder.bindInstanceFields(mainView.getBookEditor());

        mainView.getBookEditor().getSave().addClickListener(e -> save());
        mainView.getBookEditor().getDelete().addClickListener(e -> delete());
        mainView.getBookEditor().getCancel().addClickListener(e -> cancel());
    }

    /**
     * Shows list of books in UI.
     *
     * @param title book title.
     */
    public void showBook(String title) {
        if (title.isEmpty()) {
            mainView.getGrid().setItems(bookRepo.findAll());
        } else {
            mainView.getGrid().setItems(bookRepo.findByTitle(title));
        }
    }

    /**
     * Edits given book object.
     *
     * @param newBook book to edit.
     */
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

    /**
     * Deletes a book from list and repository.
     */
    private void delete() {
        bookRepo.delete(book);
        mainView.getBookEditor().getChangeHandler().onChange();
    }

    /**
     * Saves a book to a list and repository.
     */
    private void save() {
        mainView.getBookEditor().getDialog().removeAll();
        if (!Validator.isbnIsValid(mainView.getBookEditor().getIsbn().getValue())
                || !Validator.priceIsValid(mainView.getBookEditor().getPrice().getValue())
                || mainView.getBookEditor().getTitle().getValue().isEmpty()) {
            mainView.getBookEditor().getDialog().add("Wrong data. Isbn must be an 10 digits positive value and price should be greater than 0.");
            mainView.getBookEditor().getDialog().open();
        } else {
            bookRepo.save(book);
            mainView.getBookEditor().getChangeHandler().onChange();
        }
    }

    /**
     * Closes book editor.
     */
    public void cancel() {
        mainView.getBookEditor().setVisible(false);
    }
}
