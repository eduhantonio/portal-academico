package br.edu.fatec.listabd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import br.edu.fatec.listabd.model.Aluno;

public class AlunoAdapter extends BaseAdapter {

    // -------------------------- Getters and Setters -------------------------- //

    private List<Aluno> alunos;
    private LayoutInflater inflater;

    public AlunoAdapter(Context context, List<Aluno> alunos) {
        this.alunos = alunos;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    // -------------------------- Conversão da Visualização -------------------------- //

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_lista_aluno, parent, false);
            holder = new ViewHolder();
            holder.txtName = convertView.findViewById(R.id.listName);
            holder.txtId = convertView.findViewById(R.id.listID);
            holder.txtCPF = convertView.findViewById(R.id.listCPF);
            holder.txtTelefone = convertView.findViewById(R.id.listTelefone);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // -------------------------- Obtenção e Configuração do Aluno -------------------------- //

        Aluno aluno = alunos.get(position);
        holder.txtName.setText("Nome: " + aluno.getNome());
        holder.txtId.setText("ID: #" + aluno.getId());
        holder.txtCPF.setText("CPF: " + aluno.getCpf());
        holder.txtTelefone.setText("Telefone: " + aluno.getTelefone());

        return convertView;
    }

    private static class ViewHolder {
        TextView txtName;
        TextView txtId;
        TextView txtCPF;
        TextView txtTelefone;
    }
}
