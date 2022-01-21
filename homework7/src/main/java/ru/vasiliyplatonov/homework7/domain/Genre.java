package ru.vasiliyplatonov.homework7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "genres")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Exclude
	private Long id;

	@NaturalId(mutable = true)
	@Column(name = "name", unique = true, nullable = false)
	private String name;

	public Genre(String name) {
		this.name = name;
	}

}
