package es.codeurjc.ais.unitary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import es.codeurjc.ais.book.*;
import es.codeurjc.ais.book.OpenLibraryService.BookData;
import es.codeurjc.ais.notification.NotificationService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;

import es.codeurjc.ais.book.BookDetail;
import es.codeurjc.ais.review.Review;
import es.codeurjc.ais.review.ReviewRepository;
import es.codeurjc.ais.review.ReviewService;

@DisplayName("BookService Unitary tests")
public class ReviewServiceUnitaryTest {



    OpenLibraryService openLibraryService = mock(OpenLibraryService.class);
    NotificationService notificationService = mock(NotificationService.class);
    


    @Test
    public void getReviewsByIdUnitTest(){

        Review review1 = new Review();
        review1.setBookId("123A");
        review1.setNickname("nickname1");
        review1.setContent("content1");

        Review review2 = new Review();
        review2.setBookId("123A");
        review2.setNickname("nickname2");
        review2.setContent("content2");

        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        when(reviewRepository.findByBookId("123A")).thenReturn(Arrays.asList(review1, review2));
        NotificationService notificationService = mock(NotificationService.class);
        ReviewService reviewService = new ReviewService(reviewRepository, notificationService);

        assertEquals(2, reviewService.findByBookId("123A").size());


    }
     
    @Test
    public void testMagic()
    {  
        List <BookData> books = new ArrayList<BookData>();
        BookData book1 = new BookData("Titulo", "/ejemplo/Titulo", "description", new Integer[]{1,2,3}, new String[]{"magic", "fantasy"});
        BookData book2 = new BookData("Titulo2", "/ejemplo/Titulo2", "description2", new Integer[]{1,2,3}, new String[]{"magic", "fantasy"});
        books.add(book2);
        books.add(book1);
        when(openLibraryService.searchBooks(anyString(),anyInt())).thenReturn(books);

        BookService bookService = new BookService(openLibraryService, notificationService);
        
        assertEquals(bookService.findAll("magic").size(), 2);
        
    }

    @Test
    public void testID()
    {
        BookService bookService = new BookService(openLibraryService, notificationService);
        try
        {
        	Optional<BookDetail> book = bookService.findById("OL138052W");
            HttpClientErrorException exception = new HttpClientErrorException(HttpStatusCode.valueOf(404));
            when(book).thenThrow(exception);
            	
        }
        catch(NullPointerException e)
        {
        	BookDetail book = null;
            HttpClientErrorException exception = new HttpClientErrorException(HttpStatusCode.valueOf(404));
            when(book).thenThrow(exception);

           }   
    }
}
