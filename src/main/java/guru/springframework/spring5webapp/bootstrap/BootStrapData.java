package guru.springframework.spring5webapp.bootstrap;

import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner{


    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }



    @Override
    public void run(String... args) throws Exception {
        
        Author eric = new Author("Eric", "Evans", new HashSet<>());
        Book ddd = new Book("Domain Driver Design", "123123", new HashSet<>());
        Publisher orelly = new Publisher("o'relly", "Bodega Avenue", "Sebastopol", "California", "95472", new HashSet<>());

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        publisherRepository.save(orelly);
        
        System.out.println("Number of publishers: "+publisherRepository.count());

        ddd.setPublisher(orelly);
        orelly.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(orelly);

        Author rod = new Author("Rod", "Johnson", new HashSet<>());
        Book noEJB = new Book("J2EE Development without EJB", "3939459459", new HashSet<>());

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        orelly.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(orelly);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: "+ bookRepository.count());
        System.out.println("Publisher Number of Books: "+ orelly.getBooks().size());
    }
    
}
