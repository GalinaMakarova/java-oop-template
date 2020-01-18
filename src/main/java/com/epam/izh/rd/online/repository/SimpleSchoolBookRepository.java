package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[]{};

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] booksArrayForReplacing = new SchoolBook[count() + 1];
        System.arraycopy(schoolBooks, 0, booksArrayForReplacing, 0, count());
        booksArrayForReplacing[booksArrayForReplacing.length - 1] = book;
        schoolBooks = booksArrayForReplacing;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int bookAppearCount = 0;
        if (name != null) {
            for (int i = 0; i < count(); i++) {
                if (schoolBooks[i].getName().equals(name)) {
                    bookAppearCount++;
                }
            }
        }
        if (bookAppearCount > 0) {
            SchoolBook[] resultBooksArray = new SchoolBook[bookAppearCount];
            int j = 0;
            for (int i = 0; i < count(); i++) {
                if (schoolBooks[i].getName().equals(name)) {
                    resultBooksArray[j] = schoolBooks[i];
                    j++;
                }
            }
            return resultBooksArray;
        } else {
            return new SchoolBook[0];
        }
    }

    @Override
    public boolean removeByName(String name) {
        int bookAppearCount = findByName(name).length;
        if (name != null) {
            SchoolBook[] booksArrayForReplacing = new SchoolBook[count() - bookAppearCount];
            int booksArrayForReplacingIndex = 0;
            for (int i = 0; i < count(); i++) {
                if (!schoolBooks[i].getName().equals(name)) {
                    booksArrayForReplacing[booksArrayForReplacingIndex] = schoolBooks[i];
                    booksArrayForReplacingIndex++;
                }
            }
            schoolBooks = booksArrayForReplacing;
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
