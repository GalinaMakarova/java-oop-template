package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[]{};

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] buf = new SchoolBook[count()+1];
        System.arraycopy(schoolBooks, 0, buf, 0, count());
        buf[buf.length - 1] = book;
        schoolBooks = buf;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int N = 0; //считаем, что  списке книги с таким названием нет
        SchoolBook book = new SchoolBook(); //сюда запишем книгу, если такая будет
        for(int i = 0; i < count(); i++){
            if (schoolBooks[i].getName().equals(name)){
                N++; //увеличиваем счетчик на 1
                book = schoolBooks[i];
            }
        }
        if (N > 0) {
            SchoolBook[] buf = new SchoolBook[N];
            for (int i = 0; i < N; i++){
                buf[i] = book;
            }
            return buf;
        } else {
            return new SchoolBook[0];
        }
    }

    @Override
    public boolean removeByName(String name) {
        if (findByName(name).length > 0) {

            int N = 0; //считаем, что  списке книги с таким названием нет
            for (int i = 0; i < count(); i++) {
                if (schoolBooks[i].getName().equals(name)) {
                    N++; //увеличиваем счетчик на 1
                }
            }
            SchoolBook[] buf = new SchoolBook[count() - N]; //создали новый массив, куда не будем записывать ту книгу, которую удаляем
            int j = 0; //счетчик для вспомогательного массива
            for (int i = 0; i < count(); i++) {
                if (!schoolBooks[i].getName().equals(name)) {
                    buf[j] = schoolBooks[i];
                    j++;
                }
            }
            schoolBooks = buf;
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
