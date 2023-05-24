package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Table(name = "tb_medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor // gerar construtor Default sem argumentos, JPA exige em todas entidades
@AllArgsConstructor // construtor para receber todos os campos
@EqualsAndHashCode(of = "id") //gerar o equals com o id
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded // considera outra classe na mesma tabela
    private Endereco endereco;

    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }
}
