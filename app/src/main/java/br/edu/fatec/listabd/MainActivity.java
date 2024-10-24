package br.edu.fatec.listabd;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.edu.fatec.listabd.dao.AlunoDAO;
import br.edu.fatec.listabd.model.Aluno;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private AlunoDAO alunoDAO;
    private List<Aluno> listaAlunos;

    // -------------------------- Inicialização -------------------------- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listViewAlunos);
        alunoDAO = new AlunoDAO(this);

        // Carregar a lista de alunos do banco de dados
        carregarListaAlunos();

        // Configurar o clique na ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoSelecionado = listaAlunos.get(position);
                Intent intent = new Intent(MainActivity.this, ModificarAlunoActivity.class);
                intent.putExtra("alunoId", alunoSelecionado.getId());
                startActivity(intent);
            }
        });
    }

    // -------------------------- Carregando Listas -------------------------- //

    // Recarregar a lista de alunos ao retornar para a MainActivity
    @Override
    protected void onResume() {
        super.onResume();
        carregarListaAlunos();
    }

    private void carregarListaAlunos() {
        listaAlunos = alunoDAO.obterTodos();  // Obtém a lista de alunos do banco de dados
        AlunoAdapter adapter = new AlunoAdapter(this, listaAlunos);  // Usa o AlunoAdapter personalizado
        listView.setAdapter(adapter);  // Define o adapter no ListView
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // -------------------------- Botões do Menu -------------------------- //

    public void sairTudo(MenuItem item){
        finish();
    }

    public void novo(MenuItem item){
        Intent intent = new Intent(this, CadastroAlunoActivity.class);
        startActivity(intent);
    }
}
