package dao.impl;

import java.util.List;

import dao.BookBase;
import dao.BookDao;
import entity.Book;

public class BookDaoImpl extends BookBase implements BookDao {

	@Override
	public List<Book> queryAll() {
		String sql = "select * from book";
		Object[] params = {};
		return executeQuery(sql, params, Book.class);
	}

	@Override
	public int insert(Book book) {
		String sql = "insert into book(bname,author,price) values(?,?,?)";
		Object[] params = {book.getBname(),book.getAuthor(),book.getPrice()};
		return executeUpdate(sql, params);
	}

	@Override
	public int delete(int bid) {
		String sql = "delete from book where bid = ?";
		Object[] params = {bid};
		return executeUpdate(sql, params);
	}

	@Override
	public Book queryOne(int bid) {
		String sql = "select * from book where bid = ?";
		Object[] params  = {bid};
		List<Book> books = executeQuery(sql, params, Book.class);
		if(books.isEmpty()){
			return null;
		}else{
		    return books.get(0);
		}
	}

	@Override
	public int update(Book book) {
		String sql = "update book set bname = ?,author=?,price=? where bid = ?";
		Object[] params = {book.getBname(),book.getAuthor(),book.getPrice(),book.getBid()};
		return executeUpdate(sql, params);
	}
	

}
