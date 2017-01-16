// BookManager.aidl
package com.mz.sanfen.ipc;

// Declare any non-default types here with import statements

import com.mz.sanfen.ipc.Book;

interface BookManager {
    //所有的返回值前不需要加任何东西，不管是什么数据类型
    List<Book> getBooks();

    //传参时除了Java基本类型以及String, CharSequence之外的类型
    //都需要在前面加上定向tag
    void addBook(in Book book);
}
