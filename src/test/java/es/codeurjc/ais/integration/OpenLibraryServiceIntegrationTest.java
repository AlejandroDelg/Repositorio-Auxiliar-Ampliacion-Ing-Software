package es.codeurjc.ais.integration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.codeurjc.ais.book.OpenLibraryService;
import es.codeurjc.ais.book.OpenLibraryService.BookData;

@DisplayName("OpenLibraryService integration tests")
public class OpenLibraryServiceIntegrationTest {

    OpenLibraryService openLibraryService = new OpenLibraryService();
    
    @Test
	public void obtainListOfBooksByTopicTest(){
       
        List<BookData> books = openLibraryService.searchBooks("drama", 15);

        assertEquals(15, books.size());
  
    }

    @Test
    public void topic()
    {
        List<BookData> drama= openLibraryService.searchBooks("drama", 15);
        List<BookData> fantasy= openLibraryService.searchBooks("fantasy", 15);
        List<BookData> magic= openLibraryService.searchBooks("magic", 15);

        assertEquals(drama.size(), 15);
        assertEquals(fantasy.size(), 15);
        assertEquals(magic.size(), 15);

    }

    @Test
    public void theNameOfTheWind()
    {
        BookData book = openLibraryService.getBook("OL8479867W");
        assertNotEquals(book , null);
        assertDoesNotThrow(()->{
        	System.out.println("Se ha lanzado una excepcion");
        });
    }
    
}
