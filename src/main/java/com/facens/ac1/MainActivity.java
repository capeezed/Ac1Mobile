package com.facens.ac1;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtDescricao, edtValor, edtData;
    Spinner spinnerCategoria, spinnerPagamento;
    CheckBox checkFoiPaga;
    Button btnSalvar;
    ListView lista;

    BancoHelper banco;
    ArrayAdapter<String> adapter;
    ArrayList<String> despesas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtDescricao = findViewById(R.id.edtDescricao);
        edtValor = findViewById(R.id.edtValor);
        edtData = findViewById(R.id.edtData);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        spinnerPagamento = findViewById(R.id.spinnerPagamento);
        checkFoiPaga = findViewById(R.id.checkFoiPaga);
        btnSalvar = findViewById(R.id.btnSalvar);
        lista = findViewById(R.id.lista);

        banco = new BancoHelper(this);

        ArrayAdapter<CharSequence> AdapterCategoria = ArrayAdapter.createFromResource(
                this, R.array.categorias, android.R.layout.simple_spinner_item);
        spinnerCategoria.setAdapter(AdapterCategoria);

        ArrayAdapter<CharSequence> AdapterPagamentos = ArrayAdapter.createFromResource(
                this, R.array.pagamentos, android.R.layout.simple_spinner_item);
        spinnerPagamento.setAdapter(AdapterPagamentos);



        despesas = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, despesas);
        lista.setAdapter(adapter);

        btnSalvar.setOnClickListener(v -> salvar());

        lista.setOnItemLongClickListener((parent, view, position, id) -> {
            banco.excluir(position + 1);
            listar();
            return true;
        });
        listar();
    }



    private void salvar() {

        Despesa d = new Despesa(
                edtDescricao.getText().toString(),
                spinnerCategoria.getSelectedItem().toString(),
                Integer.parseInt(edtValor.getText().toString()),
                Integer.parseInt(edtData.getText().toString()),
                spinnerPagamento.getSelectedItem().toString(),
                checkFoiPaga.isChecked()
                );
        banco.inserir(d);
        listar();
    }

    private void listar() {
        despesas.clear();

        Cursor c = banco.listar();
        while(c.moveToNext()) {
            String txt = c.getString(1) + " - " +
                    c.getString(2) + " - " +
                    c.getInt(3) + " - " +
                    c.getInt(4) + " - " +
                    c.getString(5) + " - " +
                    c.getString(6);
            despesas.add(txt);
        }
        adapter.notifyDataSetChanged();
    }



}

