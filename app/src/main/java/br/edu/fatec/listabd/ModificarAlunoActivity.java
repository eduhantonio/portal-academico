package br.edu.fatec.listabd;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.fatec.listabd.dao.AlunoDAO;
import br.edu.fatec.listabd.model.Aluno;

public class ModificarAlunoActivity extends AppCompatActivity {

    private EditText editTextNome, editTextCpf, editTextTelefone;
    private Button buttonAtualizar, buttonExcluir;
    private AlunoDAO alunoDAO;
    private Aluno aluno;

    // -------------------------- Carregando dados dos Alunos -------------------------- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_modificar);

        editTextNome = findViewById(R.id.editTextNome);
        editTextCpf = findViewById(R.id.editTextCpf);
        editTextTelefone = findViewById(R.id.editTextTelefone);
        buttonAtualizar = findViewById(R.id.buttonAtualizar);
        buttonExcluir = findViewById(R.id.buttonExcluir);

        alunoDAO = new AlunoDAO(this);

        // Recuperar o ID do aluno enviado pela Intent
        int alunoId = getIntent().getIntExtra("alunoId", -1);

        if (alunoId != -1) {
            aluno = alunoDAO.read(alunoId);

            // Preencher os campos com os dados do aluno
            editTextNome.setText(aluno.getNome());
            editTextCpf.setText(aluno.getCpf());
            editTextTelefone.setText(aluno.getTelefone());
        }

        // Ação do botão "Atualizar"
        buttonAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarAluno();
            }
        });

        // Ação do botão "Excluir"
        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirAluno();
            }
        });
    }

    // -------------------------- Métodos de Modificação -------------------------- //

    private void atualizarAluno() {
        String nome = editTextNome.getText().toString();
        String cpf = editTextCpf.getText().toString();
        String telefone = editTextTelefone.getText().toString();

        // Atualizar os dados do aluno
        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setTelefone(telefone);

        alunoDAO.update(aluno);
        Toast.makeText(this, "Aluno atualizado com sucesso!", Toast.LENGTH_SHORT).show();
        finish();  // Fecha a tela de modificação e volta para a tela principal
    }

    // Método para excluir o aluno do banco de dados
    private void excluirAluno() {
        alunoDAO.delete(aluno);
        Toast.makeText(this, "Aluno excluído com sucesso!", Toast.LENGTH_SHORT).show();
        finish();  // Fecha a tela de modificação e volta para a tela principal
    }
}
