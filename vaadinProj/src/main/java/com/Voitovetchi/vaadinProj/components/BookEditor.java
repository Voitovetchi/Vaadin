package com.Voitovetchi.vaadinProj.components;

import com.Voitovetchi.vaadinProj.domain.Book;
import com.Voitovetchi.vaadinProj.repository.BookRepo;
import com.Voitovetchi.vaadinProj.services.Validator;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Getter;
import lombok.Setter;

@SpringComponent
@UIScope
@Getter
public class BookEditor extends VerticalLayout implements KeyNotifier {
    private final BookRepo bookRepo;



    private Book book;

    Dialog dialog = new Dialog();

    TextField isbn = new TextField("isbn");
    TextField title = new TextField("title");
    TextField price = new TextField("price");

    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);


    @Setter
    private ChangeHandler changeHandler;

    public interface ChangeHandler {
        void onChange();
    }

    public BookEditor(BookRepo bookRepo) {
        this.bookRepo = bookRepo;

        add(isbn, price, title,  actions);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        setVisible(false);
    }


}
