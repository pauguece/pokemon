package guevara.paulina.Pokemon.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pokemones")
@Data                 // getters, setters, toString, equals y hashCode
@NoArgsConstructor    // constructor vacío (requerido por JPA)
@AllArgsConstructor   // constructor con todos los atributos
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrementable
    private Long id;

    private String nombre;
    private String tipo;
    private Integer nivel;
    private Integer ataque;
    private Integer defensa;
    @Column(length = 500)
    private String descripcion;
    private String imagenUrl;
}
