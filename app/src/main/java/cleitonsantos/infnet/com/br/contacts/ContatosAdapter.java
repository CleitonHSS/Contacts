package cleitonsantos.infnet.com.br.contacts;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ContatosAdapter extends RecyclerView.Adapter<ContatosAdapter.ContatosListHolder> {

    private List<Contato> contatoList;

    public ContatosAdapter(List<Contato> contatoList) {
        this.contatoList = contatoList;
    }

    @NonNull
    @Override
    public ContatosListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = R.layout.contato_card;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ContatosListHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContatosListHolder holder, int position) {
        if (contatoList != null && position < contatoList.size()) {
            Contato contato = contatoList.get(position);
            holder.nome.setText(contato.getNome());
            holder.email.setText(contato.getEmail());
            holder.senha.setText(contato.getSenha());
            holder.cpf.setText(contato.getCpf());
        }
    }

    @Override
    public int getItemCount() {
        return contatoList.size();
    }

    class ContatosListHolder extends RecyclerView.ViewHolder {

        public TextView nome;
        public TextView email;
        public TextView senha;
        public TextView cpf;



        ContatosListHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nome);
            email = itemView.findViewById(R.id.email);
            senha = itemView.findViewById(R.id.senha);
            cpf = itemView.findViewById(R.id.cpf);


            itemView.setOnClickListener(new View.OnClickListener() {
                boolean opencard = true;
                @Override
                public void onClick(View view) {
                    LinearLayout lview = view.findViewById(R.id.l_view);
                    if (opencard){
                        lview.setVisibility(view.VISIBLE);
                    } else {
                        lview.setVisibility(view.GONE);
                    }
                    opencard = !opencard;
                }
            });

        }
    }
}
