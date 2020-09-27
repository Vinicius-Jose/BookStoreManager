package entity;

import javax.persistance.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullabe=false, unique=true)
	private String name;
	
	@Column(nullabe=false)
	private Integer age;

}
