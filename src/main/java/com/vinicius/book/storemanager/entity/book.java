package entity;



import Author;

import javax.persistance.*;

@Entity
public class Book {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullabe=false, unique=true)
	private String name;
	
	@Column(nullabe=false)
	private Integer pages;

    
	@Column(nullabe=false)
	private Integer chapters;

    
	@Column(nullabe=false, unique=true)
	private String isbn;

    
	@Column(nullabe=false,name="publisher_name")
	private String publisherName;


    
	@ManyToOne(fetch=FetchType.Lazy,cascade=true)
    @JoinColumn(name="author_id")
	private Author author;
}