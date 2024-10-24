package br.edu.fatec.listabd;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.fatec.listabd.dao.AlunoDAO;
import br.edu.fatec.listabd.model.Aluno;

public class CadastroAlunoActivity extends AppCompatActivity {

    private EditText editTextNome, editTextCpf, editTextTelefone;
    private Button buttonSalvar, buttonSair;
    private AlunoDAO alunoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_cadastro);

        // -------------------------- Componentes -------------------------- //

        editTextNome = findViewById(R.id.editTextNome);
        editTextCpf = findViewById(R.id.editTextCpf);
        editTextTelefone = findViewById(R.id.editTextTelefone);
        buttonSalvar = findViewById(R.id.buttonSalvarNM);
        buttonSair = findViewById(R.id.buttonSairNM);

        // -------------------------- Configurações Iniciais -------------------------- //

        // Conexão com o Banco de Dados
        alunoDAO = new AlunoDAO(this);

        // Desativando o botão Salvar
        buttonSalvar.setEnabled(false);

        // Listener para habilitar o botão "Salvar" somente quando todos os campos estiverem preenchidos
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                verificarCampos();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        editTextNome.addTextChangedListener(textWatcher);
        editTextCpf.addTextChangedListener(textWatcher);
        editTextTelefone.addTextChangedListener(textWatcher);

        // -------------------------- Ação dos Botões -------------------------- //

        // Botão Salvar
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarAluno();
            }

        });

        // Botão Sair
        buttonSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sairAluno();
            }
        });
    }

    // -------------------------- Verificar Campos Preenchidos -------------------------- //

    private void verificarCampos() {
        String nome = editTextNome.getText().toString();
        String cpf = editTextCpf.getText().toString();
        String telefone = editTextTelefone.getText().toString();

        // Habilita o botão "Salvar" somente se todos os campos estiverem preenchidos
        buttonSalvar.setEnabled(!nome.isEmpty() && !cpf.isEmpty() && !telefone.isEmpty());
    }

    // -------------------------- Salvar Aluno no BD -------------------------- //

    private void salvarAluno() {
        String nome = editTextNome.getText().toString();
        String cpf = editTextCpf.getText().toString();
        String telefone = editTextTelefone.getText().toString();

        // Criando um novo objeto Aluno
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setTelefone(telefone);

        // Salvando no Banco de Dados
        long id = alunoDAO.insert(aluno);

        // -------------------------- Mensagens de Confirmação -------------------------- //
        if (id > 0) {
            Toast.makeText(this, "Aluno ID: #"+id+" cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            editTextNome.setText(null);
            editTextCpf.setText(null);
            editTextTelefone.setText(null);
        } else {
            Toast.makeText(this, "Erro ao cadastrar aluno!", Toast.LENGTH_SHORT).show();
        }
    }

    // -------------------------- Sair da Tela de Cadastro -------------------------- //
    private void sairAluno(){
        finish();
    }
}
