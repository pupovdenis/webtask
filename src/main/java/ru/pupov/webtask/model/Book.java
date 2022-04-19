package ru.pupov.webtask.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "BOOKS")
public class Book {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_generator")
    @SequenceGenerator(name = "books_generator", sequenceName = "books_seq", allocationSize = 1)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "RELEASE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @Column(name = "IS_PRIVATE")
    private Boolean isPrivate;

    public boolean isCorrectToSave() {
        return releaseDate != null
                && name != null && !name.isEmpty()
                && author != null && !author.isEmpty();
    }

    public String toShortString() {
        return String.format("%s(%s)", name, author);
    }
}
