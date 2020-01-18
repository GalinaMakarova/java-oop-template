package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[]{};

    @Override
    public boolean save(Author author) {
        if (author != null && findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }
        Author[] buf = new Author[count() + 1];
        System.arraycopy(authors, 0, buf, 0, count());
        buf[buf.length - 1] = author;
        authors = buf;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        if (name != null || lastname != null)
            for (int i = 0; i < count(); i++) {
                if (authors[i].getName().equals(name) && authors[i].getLastName().equals(lastname)) {
                    return authors[i];
                }
            }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (author != null && findByFullName(author.getName(), author.getLastName()) != null) {
            int k = 0;
            if (count() > 1) {
                Author[] buf = new Author[count() - 1];
                for (int i = 0; i < count(); i++) {
                    if (authors[i].getName().equals(author.getName()) && authors[i].getLastName().equals(author.getLastName())) {
                        k = i;
                        break;
                    }
                }
                System.arraycopy(authors, 0, buf, 0, k);
                System.arraycopy(authors, k + 1, buf, k, buf.length);
                authors = buf;
            } else {
                authors = new Author[]{};
            }
            return true;
        } else return false;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
