package com.example.backend.dao.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "exercices")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Exercice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String title ;
    private String description ;
    private String content ;
    private String image ;

    @ManyToOne
    @JoinColumn(name = "subject_id"  , nullable = false)
    private Subject subject ;

    @ManyToOne
    @JoinColumn(name = "level_id" , nullable = false)
    private Level level ;

    @OneToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "solution_id" , nullable = true)
    private Solution solution;

    @ManyToOne
    @JoinColumn(name = "enseignant_id" , nullable = false)
    private User user ;
    
}
