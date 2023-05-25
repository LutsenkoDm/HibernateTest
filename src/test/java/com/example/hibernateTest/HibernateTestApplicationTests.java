package com.example.hibernateTest;

import com.example.hibernateTest.service.AuthorService;
import com.example.hibernateTest.service.SingleIntService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ForkJoinPool;

@SpringBootTest
class HibernateTestApplicationTests {

	// Lazy Eager
	// N+1
	// Batch size
	// Entity graph
	// Оптимистичные блокировки

	@Autowired
	AuthorService authorService;

	@Autowired
	SingleIntService singleIntService;

	@Test
	void NPlus1() {
		authorService.create3WithLazyBooks();
		authorService.getNameContaining("A");
	}

	@Test
	void lazyEager() {
		authorService.create3WithLazyBooks();
		authorService.create3WithEagerBooks();

		authorService.getFirstLazy();
		authorService.getFirstEager();
	}

	@Test
	void lazyEagerBatched() {
		authorService.create3WithLazyBooks();
		authorService.create3WithEagerBooks();
		authorService.create3WithBatchedBooks();

		authorService.getAllLazy();
		authorService.getAllEager();
		authorService.getAllBatched();
	}

	@Test
	void locks() throws InterruptedException {
		singleIntService.create3();
		ForkJoinPool.commonPool().execute(() -> singleIntService.update(1111));
		Thread.sleep(100);
		singleIntService.update(2222);
	}

//  Размер session пула
	// ОДин метод зовет другой тран-й required new
}
