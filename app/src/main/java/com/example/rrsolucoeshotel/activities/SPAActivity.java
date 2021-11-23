package com.example.rrsolucoeshotel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rrsolucoeshotel.R;
import com.example.rrsolucoeshotel.adapter.AdapterProdutos;
import com.example.rrsolucoeshotel.model.ProdutosServicosHotel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SPAActivity extends AppCompatActivity {

    private RecyclerView recyclerProdutos;
    private List<ProdutosServicosHotel> listaSPA= new ArrayList<>();

    private String nomeHospede, cpfHospede;
    private int[] quantidade = {1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spaactivity);


        IniciarComponentes();

        // Criar listagem de Produtos
        this.criarProdutosRestaurantes();


        // Configurar Adapter
        // Esse cara faz a exibição da lista no app - Necessita criar um construtor para classe adapter receber uma lista
        // Fazer isso dentro do AdapterProdutos.java
        AdapterProdutos adapter = new AdapterProdutos(listaSPA);


        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerProdutos.setLayoutManager(layoutManager);
        recyclerProdutos.setHasFixedSize(true); // Tamanho fixo para otimizar o layout
        recyclerProdutos.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerProdutos.setAdapter( adapter );
    }

    private void IniciarComponentes() {
        nomeHospede = getIntent().getStringExtra("nomeHospede");
        cpfHospede = getIntent().getStringExtra("cpfHospede");

        recyclerProdutos = findViewById(R.id.recyclerViewSPA);
    }

    public void criarProdutosRestaurantes(){

        ProdutosServicosHotel produto = new ProdutosServicosHotel("Banho de Sais", "80", "Maravilhoso banho com nossos melhores sais de banho");
        this.listaSPA.add( produto );

        produto = new ProdutosServicosHotel("Hidromassagem", "60", "Massagem dentro da piscina climatizada");
        this.listaSPA.add( produto );

        produto = new ProdutosServicosHotel("Massagem Relaxante", "120", "Massagem para relaxar e eliminar o estresse");
        this.listaSPA.add( produto );

        produto = new ProdutosServicosHotel("Esfoliação e Hidratação Corporal", "80", "Tratamento facial rejuvenecedor");
        this.listaSPA.add( produto );

        produto = new ProdutosServicosHotel("Massagem Com pedras Quentes", "120", "Deliciosa massagem relaxante com tecnica de pedras temperadas");
        this.listaSPA.add( produto );

        produto = new ProdutosServicosHotel("Massagem a Quatro Mãos", "28", "Massagem relaxante a Quatro Mãos especializadas");
        this.listaSPA.add( produto );

        produto = new ProdutosServicosHotel("Massagem com Final Feliz", "250", "É serio");
        this.listaSPA.add( produto );

        produto = new ProdutosServicosHotel("Quick Massage", "60", "Massagem rapida para os Atarefados");
        this.listaSPA.add( produto );

        produto = new ProdutosServicosHotel("Massagem com Bambu", "120", "Sim! Bambus mesmo.");
        this.listaSPA.add( produto );

        produto = new ProdutosServicosHotel("Avaliação Estética", "0", "Faça uma avaliação Gratuita Conosco");
        this.listaSPA.add( produto );
    }

    //Molde de data caixa de mensagem para quando for confirmar o pedido
    private void CriaCaixaDialogo() {
        int[] quantidade = {1};

        AlertDialog.Builder criaCaixa = new AlertDialog.Builder(this);
        criaCaixa.setTitle("Confirmação de compra");
        criaCaixa.setIcon(R.drawable.ic_feedback);

        View caixaView = getLayoutInflater().inflate(R.layout.adapter_alertdialog_confirmar_produto,
                null);
        criaCaixa.setView(caixaView);

        criaCaixa.setPositiveButton("Confimar", (dialogInterface, i) -> {
            //DadosHospede dadosHospede = new DadosHospede();

            //dadosHospede.setNome(nomeHospede);
            //dadosHospede.setsetCPF(cpfHospede);
            //dadosHospede.setDescricao(FaltaDESCRICAO);
            //dadosHospede.setValor_Produto(String.valueOf(FaltaVALOR_PRODUTO));
            //dadosHospede.setQuantidade(String.valueOf(quantidade[0]));

            //double VALOR_TOTAL = quantidade[0] * VALOR_PRODUTO;
            //dadosHospede.setValor_Total(String.valueOf(FaltaVALOR_TOTAL));
            //dadosHospede.setData(PegaDataAtual());

            //RegistrarConsumoHospede(dadosHospede);
            Log.w("Confirmar pedido", PegaDataAtual());
        });
        criaCaixa.setNegativeButton("Cancelar",
                (dialogInterface, i) -> Log.w("Cancelar pedido", PegaDataAtual()));

        TextView descricaoProduto =  caixaView.findViewById(R.id.txtDescricaoAlertDialog);
        TextView quantidadeProduto = caixaView.findViewById(R.id.txtQuantidadeAlertDialog);

        SubtraiAQuantidade(quantidade, caixaView, quantidadeProduto);
        SomaAQuantidade(quantidade, caixaView, quantidadeProduto);

        AlertDialog caixaDialogo = criaCaixa.create();
        caixaDialogo.show();
    }

    private void SubtraiAQuantidade(int[] quantidade, View caixaView, TextView quantidadeProduto) {
        Button btnMenosQuantidade = caixaView.findViewById(R.id.btnMenosQuantidadeAlertDialog);
        btnMenosQuantidade.setOnClickListener(v -> {
            if(quantidade[0] > 1){
                quantidade[0]--;
                quantidadeProduto.setText(String.valueOf(quantidade[0]));
                Log.w("Teste botão menor", "apertou botão -");
            }
        });
    }

    private void SomaAQuantidade(int[] quantidade, View caixaView, TextView quantidadeProduto) {
        Button btnMaisQuantidade = caixaView.findViewById(R.id.btnMaisQuantidadeAlertDialog);
        btnMaisQuantidade.setOnClickListener(v -> {
            quantidade[0]++;
            quantidadeProduto.setText(String.valueOf(quantidade[0]));
            Log.w("Teste botão maior", "apertou botão +");
        });
    }

    private String PegaDataAtual() {
        Locale locale = new Locale("pt", "BR");
        SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd", locale);
        Date data = new Date();
        String dataFormatada = formataData.format(data);
        Log.w("Teste pegar data", "Data sistema: " + dataFormatada );

        return dataFormatada;
    }
}
