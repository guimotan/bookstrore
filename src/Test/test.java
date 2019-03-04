package Test;

import dao.impl.BookDaoImpl;

public class test {

	public static void main(String[] args) {
		BookDaoImpl b = new BookDaoImpl();
		System.out.println(b.queryAll());

	}

}
