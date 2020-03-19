package script_preenchimento.hibernate;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import netscape.javascript.JSObject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListArtigos {

    public static final int LISTAR = 1;
    public static final int ADICIONAR = 2;
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ListArtigos cp = new ListArtigos();
        int opcao=8;
        MongoClientURI uri = new MongoClientURI("mongodb+srv://talita:tally@cluster0-qwqoz.mongodb.net/test?retryWrites=true&w=majority");
        MongoClient mongoClient = new MongoClient(uri);
        mongoClient.listDatabaseNames();
        MongoDatabase database = mongoClient.getDatabase("Cluster0");
        MongoCollection<Document> collection = database.getCollection("artigo");

        while ((opcao = cp.criaMenuPrincipal())!=0){
            if(opcao == ListArtigos.LISTAR){
                try {
                    cp.listar(collection);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(opcao == ListArtigos.ADICIONAR){
                try {
                    cp.adicionar(collection);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("Escolha uma opcao correta.");
            }
        }
    }

    public int criaMenuPrincipal(){
        int opcao;
        System.out.println("Menu de Opcoes:");
        System.out.println("-------------------");
        System.out.println("1. Listar Artigos");
        System.out.println("2. Adicionar Artigo");
        System.out.println("-------------------");
        opcao = Integer.parseInt(sc.nextLine());
        return opcao;
    }

    public void listar(MongoCollection<Document> collection)throws Exception{
        FindIterable<Document> fi = collection.find();
        MongoCursor<Document> cursor = fi.iterator();
        int count = 0;
        try {
            while(cursor.hasNext()) {
                System.out.println("---------------------------");
                count++;
                System.out.println("Count: "+count);
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
    }

    public void adicionar(MongoCollection<Document> collection)throws Exception{
        try {
            Document document = new Document();
            document.put("_artigo_id", 25000);
            document.put("artigo_resumo", "A redação deve ser feita com frases curtas e objetivas, organizadas de acordo com a estrutura do trabalho, dando destaque a cada uma das partes abordadas, assim apresentadas: Introdução - Informar, em poucas palavras, o contexto em que o trabalho se insere, sintetizando a problemática estudada. Objetivo - Deve ser explicitado claramente. ");
            document.put("artigo_arquivo", "c:/pagina/biblioteca/fpdf/font/artigo_A_redação_deve_ser_feita_com_frases_curtas_e_objetivas_IFPB.pdf");
            document.put("arquivo_qtd_revisores", 2);
            document.put("artigo_media", 10);
            document.put("artigo_titulo", "A redação deve ser feita com frases curtas e objetivas");
            collection.insertOne(document);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
