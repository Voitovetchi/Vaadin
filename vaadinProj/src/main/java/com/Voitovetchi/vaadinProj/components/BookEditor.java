package com.Voitovetchi.vaadinProj.components;

import com.Voitovetchi.vaadinProj.domain.Book;
import com.Voitovetchi.vaadinProj.repository.BookRepo;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Setter;

@SpringComponent
@UIScope
public class BookEditor extends VerticalLayout implements KeyNotifier {
    private final BookRepo bookRepo;

    private Book book;

    TextField isbn = new TextField("isbn");
    TextField title = new TextField("title");
    TextField price = new TextField("price");

    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    private Binder<Book> binder = new Binder<>(Book.class);
    @Setter
    private ChangeHandler changeHandler;

    public interface ChangeHandler {
        void onChange();
    }

    public BookEditor(BookRepo bookRepo) {
        this.bookRepo = bookRepo;

        add(isbn, price, title,  actions);

        binder.bindInstanceFields(this);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> cancel());
        setVisible(false);
    }

    private void delete() {
        bookRepo.delete(book);
        changeHandler.onChange();
    }

    private void save() {
        bookRepo.save(book);
        changeHandler.onChange();
    }

    public void cancel() {
        setVisible(false);
    }

    public void editBook(Book newBook) {
        if (newBook == null) {
            setVisible(false);
            return;
        }

        if (newBook.getIsbn() != null) {
            book = bookRepo.findById(newBook.getIsbn()).orElse(newBook);
        } else {
            book = newBook;
        }

        binder.setBean(book);

        setVisible(true);

        title.focus();
    }
}
