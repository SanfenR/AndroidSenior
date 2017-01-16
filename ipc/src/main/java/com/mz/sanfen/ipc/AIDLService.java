package com.mz.sanfen.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AIDLService extends Service {
    private static final String TAG = "AIDLService";


    private List<Book> mBooks = new ArrayList<>();

    private final BookManager.Stub mBookManager = new BookManager.Stub(){

        @Override
        public List<Book> getBooks() throws RemoteException {
            synchronized (this) {
                Log.e(TAG, "getBooks: " +  mBooks.toString());


                if (mBooks != null) {
                    return mBooks;
                }
                return new ArrayList<>();
            }
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            synchronized (this) {
                if (mBooks == null) {
                    mBooks = new ArrayList<>();
                }
                if (book == null) {
                    Log.e(TAG, "addBook: Book is null");
                    return;
                }
                if (!mBooks.contains(book) ){
                    mBooks.add(book);
                }

                Log.e(TAG, "addBook: " + mBooks.toString() );

            }
        }
    };



    public AIDLService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Book book = new Book();
        book.setName("now");
        book.setPrice("123");
        mBooks.add(book);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBookManager;
    }
}
