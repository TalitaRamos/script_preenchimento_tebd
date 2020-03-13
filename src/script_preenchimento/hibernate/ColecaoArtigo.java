package script_preenchimento.hibernate;
import java.util.*;

public class ColecaoArtigo {
	public static final int ADICIONAR = 1;
	public static final int PREENCHER = 2;
	Scanner sc = new Scanner(System.in);

	private void preencherBanco(ColecaoArtigo cp){
		try {
			//ADICIONAR USUÁRIOS
//		    for(int i=0;i<3;i++){
//                cp.AdicionarUsuario();
//                System.out.println("usuario: "+i);
//            }
			UsuarioController con = new UsuarioController();
		    List<Usuario>usuarioList = con.findAll();
		    //ADICONAR CARTÕES
		    for(int j=0;j<usuarioList.size();j++){
				Cartao cartao = cp.AdicionarCartao(usuarioList.get(j));
				con.AdicionarUsuarioCartao(usuarioList.get(j),cartao,PREENCHER);
			}
		    con.fechar();
		    //ADICONAR ARTIGOS
//		    for(int l=0;l<1000;l++){
//				cp.AdicionarArtigo(l);
//			}
//			cp.AdicionarArtigo(4);
//			cp.AdicionarRevisao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void AdicionarUsuario()throws Exception{
		Random rand = new Random();
		String nome = gerarNomes();
		String sobrenome = gerarSobrenome();
		String usuario_nome = nome + " " + sobrenome;
		String usuario_endereco = "Rua "+gerarEndereco();
		String usuario_telefone = gerarTelefone();
		String usuario_email = nome + "@gmail.com";
		String usuario_local_trabalho = gerarLocalTrabalho();
		int usuario_is_revisor = rand.nextInt(2);
		int usuario_is_autor = rand.nextInt(2);

		UsuarioController con = new UsuarioController();
		Usuario a = new Usuario(usuario_nome,usuario_endereco,usuario_telefone,usuario_email,usuario_local_trabalho,usuario_is_revisor,usuario_is_autor);
		//System.out.println(a.toString());
		con.salvarUsuario(a);
		con.fechar();
	}

	public void adicionarUsuarioCmd()throws Exception{
		String opcao;
		UsuarioController con = new UsuarioController();
		do {
			System.out.println("Adicionar Usuário:");
			System.out.println("-------------------");
			System.out.print("Nome:");
			String usuario_nome = sc.nextLine();
			System.out.print("Endereço:");
			String usuario_endereco = sc.nextLine();
			System.out.print("Telefone:");
			String usuario_telefone = sc.nextLine();
			System.out.print("Email:");
			String usuario_email = sc.nextLine();
			System.out.print("Local de Trabalho:");
			String usuario_local_trabalho = sc.nextLine();
			System.out.print("É revisor? \n");
			System.out.print("Digite 0 para SIM e 1 para NÃO :");
			int usuario_is_revisor = Integer.parseInt(sc.nextLine());
			while (usuario_is_revisor!=0 && usuario_is_revisor!=1){
				System.out.print("Digite 0 para SIM e 1 para NÃO :\n");
				usuario_is_revisor = Integer.parseInt(sc.nextLine());
			}
			Usuario a = new Usuario(usuario_nome,usuario_endereco,usuario_telefone,usuario_email,usuario_local_trabalho,usuario_is_revisor,0);

			con.salvarUsuario(a);
			con.fechar();
			this.addCartao(a);

			System.out.print("Deseja Adicionar mais um Usuário? [S|N]: ");
			opcao = sc.nextLine();
		} while(opcao.compareTo("S") == 0);
		con.fechar();
	}

	public  void addCartao(Usuario a) throws Exception {
		UsuarioController con = new UsuarioController();
		System.out.println("Adicionar Cartao:");
		System.out.println("-------------------");
		System.out.print("Número do Cartão:");
		String numero_cartao = sc.nextLine();
		System.out.print("Validade:");
		String validade_cartao = sc.nextLine();
		System.out.print("Marca:");
		String marcaCartao = sc.nextLine();

		Cartao cartao = new Cartao(numero_cartao, validade_cartao,marcaCartao,a);

		con.AdicionarUsuarioCartao(a,cartao,ADICIONAR);
		System.out.println(cartao.toString());
		con.fechar();
	}

	public void AdicionarArtigo(int number) throws Exception {
		Random rand = new Random();
		ArtigoController artCon = new ArtigoController();

		String artigo_titulo = "Avaliação vestibular computadorizada de pacientes com cinetose" + number;
		String artigo_resumo = " É claro que a revolução dos costumes promove a alavancagem do remanejamento dos quadros funcionais. Nunca é demais lembrar o peso e o significado destes problemas, uma vez que a complexidade dos estudos efetuados não pode mais se dissociar das diretrizes de desenvolvimento para o futuro. O empenho em analisar o julgamento imparcial das eventualidades assume importantes posições no estabelecimento do sistema de participação geral.";
		String artigo_arquivo = "c:/pagina/biblioteca/fpdf/font/artigo_"+artigo_titulo.trim().replaceAll(" ", "_")+".pdf";
		int artigo_confirma_submissao = rand.nextInt(2);;
		int artigo_qtd_revisores = rand.nextInt(6);
		double artigo_media = (Math.random() * (10 - 1 + 1) + 1);

		Artigo artigo = new Artigo(artigo_titulo,artigo_resumo,artigo_arquivo,artigo_confirma_submissao,artigo_qtd_revisores, (float) artigo_media);
		//System.out.println(artigo);
		//artCon.salvarArtigo(artigo);
		artCon.fechar();
	}

	public Cartao AdicionarCartao(Usuario usuario) throws Exception {
		Random rand = new Random();
		CartaoController carCon = new CartaoController();

		String[] marcaCartao = {"Visa", "Mastercard", "Cielo", "Golden","American Express","Aura", "Elo", "Hipercard", "MasterCard", "Sorocred", "Visa", "Cartão BNDES", "Diners Club"};
		String numero_cartao = "";
		int contador = 0;
		for(int i=0;i<16;i++){
			contador++;
			if(contador == 4){
				numero_cartao = numero_cartao + String.valueOf(rand.nextInt(9)) + " ";
				contador = 0;
			}else{
				numero_cartao = numero_cartao + String.valueOf(rand.nextInt(9));
			}
		}
		String validade_cartao = String.valueOf(aleatoriar(1,12)) + "\\"+String.valueOf(aleatoriar(2022,2050));
		int qual_marca = rand.nextInt(marcaCartao.length);

		Cartao cartao = new Cartao(numero_cartao, validade_cartao,marcaCartao[qual_marca],usuario);
		System.out.println(cartao.toString());
		carCon.fechar();
		return cartao;
	}

	public void AdicionarRevisao() throws Exception{
        Random rand = new Random();
        String palavras[]={"barraca","barriga","burro","cachorro","carro","churrasco","corrida","corrupto","errado","erro","ferrado","ferradura","ferro","garra","garrafa","gorro","horrível","irritado","jarra","serra","serrote","sorriso","terremoto","torre","bateria","cadeira","camarão","coleira","coroa","faqueiro","feira","geladeira","gorila","jacaré","lírio","madeira","muro","pera","periquito","picareta","pirata","pirueta","tabuleiro","tubarão","zero","armário","árvore","barba","barbatana","barco","borboleta","calor","carteira","cartola","catorze","cobertor","colar","corda","formiga","garfo","guardanapo","harpa","margarida","martelo","partir","porta","ralador","revólver","sorvete","tartaruga","torneira","torta","urso","verdade","verde","alegre","braço","bravo","brinco","bruxa","bruxaria","cravo","creme","crocodilo","cruzado","dragão","estrela","fruta","grande","gravata","graveto","grilo","igreja","lágrima","livro","madrugada","pedra","praia","prato","prédio","prego","primavera","refresco","trela","três","trevo","truque","zebra","assado","assadura","assobio","massa","missa","nosso","osso","passado","passagem","pássaro","passeio","passo","pêssego","pessoa","pressa","sessenta","sossegado","sossego","tosse","tossir","vassoura","capacete","cebola","cedo","cego","ceia","cena","cenoura","cereja","cesta","cidade","cigano","cigarro","cinema","circo","cisme","hélice","Lúcia","piscina","avião","balão","botão","camarão","caminhão","cidadão","dragão","feijão","fogão","irmão","leão","limão","mamão","melão","pavão","pião","televisão","tubarão","algema","colégio","frigideira","gelado","gelatina","gelo","gema","gêmeo","general","Gilberto","ginásio","girafa","girassol","mágico","página","relógio","tigela","vegetal"};
        double revisao_nota = (Math.random() * (10 - 1 + 1) + 1);
        Date revisao_data_envio = new Date();
        String comentario = "";
        int quantas_palavras = rand.nextInt();
        for(int i=0;i<quantas_palavras;i++){
            int qual_nome = rand.nextInt(palavras.length);
            comentario = comentario + " " + palavras[qual_nome];
        }

        Revisao revisao = new Revisao((float)revisao_nota,revisao_data_envio,comentario);
    }

	private String gerarNomes(){
		String[] nomes = {"Lucas", "Mateus", "Tiago", "Talita",
				"Paloma", "Tarcio", "Gabriel", "Eduardo",
				"Elisa", "Diogo", "Juca", "Romero",
				"Gabriel", "Maria", "Larissa", "Caroline",
				"Nicolau", "Guilherme", "Pedro", "Catarina",
				"Almerinda", "Fabricio", "Claudio", "Josemar",
				"Jorge", "Ana", "Tricia", "Monica","Aaron","Aarão ","Abby","Abdénago","Abdul","Abel","Abelâmio","Abelardo","Abigail ","Abílio","Abna","Abraham","Abraão","Abraim","Abrão","Absalão","Acácio","Ácil ","Acilino","Acílio","Açucena","Acúrsio","Ada","Adail","Adalberto","Adalgisa","Adália","Adalsindo","Adalsino","Adam","Adamantino","Adamastor","Adamo","Ádan","Adão","Adelaide","Adélia","Adélio","Adelindo","Adelina","Adelino","Adelmo","Ademar","Adeodato","Adério","Adérito","Adiel","Ádila","Adília","Adílio","Adner","Adolf","Adolfo","Adonai ","Adonias","Adonilo","Adónis","Adolph","Adolphe","Adorino","Adosinda","Adrian","Adriana","Adriane","Adriano","Adriel","Adrien","Adrualdo","Adruzilo","Afonsino","Afonsina","Afonso","Affonse","Afra","Afrânio","Afre","Africana","Africano","Ágata","Aghata","Agenor ","Agna","Agnelo","Agnes","Agnolo","Agostinho","Águeda","Águstin","Augustine","Austin","Aida","Aidé","Aires","Airiza","Airton","Aitor","Aisha","Aladino","Alaíde","Alamiro","Alan","Allan","Alana","Alano","Alão","Alba","Albano","Alberico","Albert","Alberta","Albertina","Alberto","Albrecht","Alcibíades","Alcides","Alcídes","Alcina","Alcindo","Alcino","Alcione","Alcíone","Aldaír","Aldair","Aldara","Aldemar","Aldenir ","Aldenora","Alder","Aldo","Aldo","Aldónio","Aldônio","Aldora","Alegria","Aleixa","Alejandra","Alejandro","Aleksandar","Aleksander","Aleksandr","Alessandra","Alessandro","Aleta","Aleu","Alex ","Alexa","Alexander","Alexandra ","Alexandre ","Alexandrina ","Alexandrino ","Alexandro","Alexandros","Alexandru","Aléxia","Alexina","Aléxio","Aléxis","Alfeu","Alfred","Alfreda","Alfredo","Ália","Aliana","Aliça","Alice","Alicia","Alícia","Alida","Alina","Aline","Alípio","Alírio","Alisande ","Álison","Alita","Alítio","Alito","Alivar ","Alix ","Alma","Almara","Almesinda","Almir","Almira","Almiro","Aloís","Aloisio","Aloísio","Aloysio","Alpoim","Altina","Altino","Aluisio","Aluísio","Aluysio","Alva","Alvarim","Alvarina","Alvarino","Alvário","Alvaro","Álvaro","Alvino","Alzira","Amadeo","Amadeu","Amadeus","Amadis","Amado","Amador","Amália","Amanda","Amandina","Amara","Amarildo","Amarílio","Amarílis","Amaro","Amauri","Amável","Amélia","Amelina","América","Américo","Amerigo","Aminadabe ","Amor","Amora","Amorim","Amorina","Amorzinda","Amós","Ana","Anna","Anne","Anabel","Anabela","Annabella","Anael ","Anaíce","Anaíde","Anaim","Anair ","Anaís","Anaisa","Anaísa","Analdina","Anália","Analice","Analide ","Analisa","Anamar","Anania ","Ananias ","Anás ","Anatilde","André","Andrea ","Andreia","Andrei","Andreas ","Andreína","Andrelina","Andreo","Andrés","Andressa","Andresa","Andrew","Andrey","Ândria","Aneide","Anésia","Anfílito","Anfíloco","Ángel","Angel","Ângela","Angélica","Angélico","Angelina","Angélique","Angelita","Ângelo","Ânia","Aniana","Anícia","Anielo","Aníria","Anísia","Anísio","Anita","Anitta","Anittinha","Anolido","Anquita","Anselmo","Anteia","Antelmo","Antera","Antero","Anthenina","Anthony","Antoine","Antonela","Antonelo","Antónia","Antônia","Antonieta","Antonina","António","Antonio","Antônio","Antuérpia","Anunciação","Anunciada","Anuque","Anusca","Aparecida","Aparício","Ápio","Apolinário","Apolo","April","Aprígio","Aquil","Aquila ","Áquila ","Aquiles","Aquilino","Aquira ","Arabela","Araci","Aradna","Aramis ","Arão","Arcádio","Arcanjo","Arcelino","Arcélio","Arcílio","Ardingue","Argemiro","Argentina","Argentino","Ari","Ária","Ariadna","Ariadne","Ariana","Ariane","Ariel ","Ariele ","Arinda","Arine ","Ariosto","Arisberto","Aristides","Aristóteles","Arlanda","Arlete","Armandina","Armandino","Armando","Armelim","Arménia","Arménio","Armindo","Arnaldo","Arnold","Arnoldo","Aron","Arquimedes","Arquimínio","Arquimino","Arsénio","Artemisa","Artemísia","Arthur","Artur","Arturo","Aruna","Ary","Aryton","Ascenso","Asélio","Áser","Ásia","Assis","Assisi","Assunção","Assunción","Assunta","Astrid","Astride","Ataíde","Atanásio","Atão","Atenais ","Átila ","Átina ","Aubie","Aubri ","Auburn","Audete","August","Augusta","Auguste","Augusto","Aura","Áurea","Aurélia","Aureliana","Áureo","Aurete","Auriana","Aurora","Ausenda","Ausendo","Austrelino","Auta","Auxília","Ava","Avelino","Aventino","Axel ","Azélio","Aziz","Azuil","B","Baddy","Becão","Beca","Becca","Bafo","Baldwin","Baliey","Baldemar","Baldomero","Banduíno","Baltasar","Biscalquin","Baptista","Baptist","Baptiste","Baqui ","Barac","Barão","Barbie","Bárbara","Bárbora","Barcino","Barney","Barnabé","Barnaby","Bartolina","Bartolommeo","Bartolomeo","Bartolomeu","Bartolo","Bartolomeus","Bartolomé","Barthelémy","Bartholomew","Bart","Bartholomäus","Basília","Basílio","Basilissa","Bastião","Bastién","Batista","Battista","Bautista","Bea","Béa","Beanina","Beatrice","Beatrix","Beatriz","Bebiana","Bebiano","Bel","Bela","Bella","Belchior","Belém","Belina","Belinda","Belisa","Ben","Benny","Bendavida","Benedita","Benedito","Benedict","Benenetto","Benevenuto","Benícia","Benicio","Benigna","Benilde","Benita","Benito","Benjamim","Benjamin","Benjamina","Bento","Benvinda","Benvindo","Berardo","Berengária","Berilo","Bernadete","Bernardete","Bernardim","Bernardina","Bernardino","Bernardo","Bérnia","Bernie","Bertila","Bertilde","Bertina","Bertino","Berto","Bertolino","Betânia","Beth","Bete","Bétia","Betina","Betino","Beto","Betsabé","Bia","Bial","Biana","Bianca","Bianka","Bianor","Bibiana","Bibili ","Bicão","Bicalho","Biel","Bill","Billy","Billie","Bijal ","Bina","Bitia","Bita","Blanca","Blandina","Blair","Blairo","Blásia","Blaze","Boanerges","Boavida","Bob","Bobby","Bobbie","Bone","Boonie","Boni","Bonie","Bóris","Boris","Branca","Brandão","Brás","Brasília","Brásia","Bráulio","Brázia","Brenao","Brenda","Breno","Brian","Briana","Brícia","Brígida","Brígido","Brigite","Brigitte","Bridget","Briolanjo","Briosa","Brites","Britney","Brittany","Brizida","Bruce","Bruna","Bruno","Brunilde","Bryan","Bubu","Butterbean","Cássia","Cacau","Cael","Caetana","Caetano","Caia","Caíco","Caio","Caleb","Calila","Calisto ","Camélia","Camila","Candice","Cândido","Cânia","Canto","Candy","Capitolina António","Carela","Cáren ","Cárin","Carina","Carisa","Carísia","Carissa","Cárita","Carla","Carlinda","Carlo","Carl","Carly","Carlos","Carlton","Carlota","Carmélia","Carmelina","Carmelinda","Carmelita","Cármen","Carmério","Carmezinda","Carmim ","Carmina","Carminda","Carminho ","Carminha","Carmo ","Carmorinda","Carol","Carole","Carolina","Caroline","Carsta","Cassandra","Cássia","Cassiano","Cassilda","Cássio","Casta","Castelina","Castelino","Castor","Castorina","Catalina","Catarina","Catarino","Caterina","Cátia ","Catila","Catilina","Cecília","Cedric","Cedrico","Célia","Celina","Celinia","Celino","Célio","Celísio","Celestino","Celsa","Célsio","Celso","Celto","Ceres ","Cesaltina","Cesária","César","Cesarina","Cesário","Césaro","Chantal","Cheep","Chip","Chirp","Charles","Charlene","Charléne","Charlie","Charlotte","Charbel ","Chayene","Cheila ","Chema","Chloe","Christoph","Christopher","Chris","Christiane","Christian","Christina","Chuck","Chucky","Cibele","Cícero","Cid","Cidália","Cidalina","Cidálio","Cidalisa","Cildo","Cília","Cílio","Cinara","Cínara","Cinderela","Cindy","Cinira","Cíntia","Cipora","Circe","Círia","Cirila","Cirilo","Cirillo","Ciro","Cita","Cizina","Clara","Clark","Clarina","Clarinda","Clarindo","Clarinha","Clarissa","Clarisse","Claudemira","Claudemiro","Claude","Claudia","Cláudia","Claudiana","Claudiano","Cláudio","Claudio","Cleia","Cleide","Clélia","Clélio","Clemência","Cleodice","Cleonice","Cleópatra","Clésia","Clésio","Clícia","Clício","Clídio","Clife","Climénia","Clívia","Cloe","Cloé","Clorinda","Clorindo","Clóvis","Clutch","Clyde","Cookie","Colete","Conceição","Concha","Consolação","Constança","Constância","Constâncio","Consulino","Copperpot","Cora","Corália","Corálio","Cordélia","Corina","Corino","Córita","Córito","Corsino","Cosete","Cosme","Cosmo","Cozmo","Cricket","Cremilda","Cremilde","Crestila","Crisália","Crisálida","Crisanta","Crisante","Crispim ","Cristela","Cristele","Cristene","Cristián","Cristiana","Cristiane","Cristiano","Cristina","Cristobal","Cristofe","Cristóforo","Cristolinda","Cristóvão","Cursino","Cyndi","Cyro","Cyril","Dácia","Dácio","Dafne","Dagmar","Dagoberto","Daina","Daisi","Daisy","Dália","Daliana","Dalida","Dalila","Dalinda","Dalva","Dámaris ","Damares","Damas ","Damião","Damien","Dana","Dânia","Daniana","Dariana","Dan","Daniel","Danïel","Daniela","Daniele","Danila","Danilo","Danijel","Dany","Danny","Dante","Daphne","Dara","Darcília","Dárcio","Dario","Darcy","Dário","Darlene","Darnela","Darque","Darwin","Dave","Davi","David","Davide","Davina","Davínia","Dawid","Dazzle","Débora","Deborah","Décia","Décio","Décimo","Deise","Deivid","Dejalme ","Dejanira","Délcio","Delcídio","Dele ","Delfim","Delfina","Delfino","Délia","Deliana","Délio","Delisa","Delmano","Delmar ","Delmina","Delmina","Delminda","Delmira","Delmiro","Delphine","Demelza","Deméter ","Demétria","Demétrio","Dener ","Denil ","Denis","Dennis","Denisa","Denise","Deodata","Deodete","Deolindo","Deonilde","Deotila","Deótila","Dércio","Derek","Derocila","Deusdedito","Dhruva ","Dialina","Diamantina","Diamantino","Diana","Didaco","Didi","Dídia","Didiana","Didier","Diego","Dieter ","Digna","Digno","Dilan","Dilermando","Diliana","Dilsa","Dimas ","Dimitri","Dimitry","Dina","Diná","Dinamene","Dinarda","Dinarta","Dinarte ","Dineia","Dinis","Diniz","Dino","Dinora","Dioclécia","Diocleciana","Diocleciano","Dioclécio","Diogo","Diomar ","Dione","Dionilde","Dionísia","Dionísio","Dioniso","Dionisodoro","Dirce","Dircea","Dircila","Dírio","Dirque","Disa","Ditza","Diva","Divo","Diza","Djalma ","Djalme ","Djalmo","Djamila","Djavan","Dmitri","Dmitry","Dólique ","Dolores","Domenico","Domingas","Domingos","Domingo","Dominic","Domínico","Dominica","Dominique","Domitila","Domitília","Domitilo","Donald","Donaldo","Donatila","Donato","Donzélia","Donzília","Donzílio","Doodle","Dora","Dorabela","Doralice","Dores","Doriana","Dóriclo","Dorina","Dorinda","Dorindo","Dorine","Dorino","Dóris","Dorisa","Dorotéia","Dorothy","Dositeu","Drusila","Druso","Duarte","Duartina","Dudley","Duílio","Dulce","Dulcelina","Dulcídia","Dulcina","Dulcineia","Dulcínio","Dúlia","Dúnia","Dúnio","Durbalino","Durval","Durvalina","Durvalino","Doug","Douglas","Eárine ","Éber","Eberardo","Ed","Eddy","Edy","Eda","Eder","Éder","Edéria","Edgar","Édi","Edina","Edine","Édipo","Edir ","Edite","Edith","Edma","Edmero","Edmur","Edna","Edo","Edoardo","Édouard","Eduard","Eduarda","Eduardo","Eduartino","Eduina","Eduíno","Edvaldo","Edvard","Edvino","Edward","Egídio","Egil","Eglantina","Eládio","Elana","Elca","Elda","Eleazar ","Electra","Eleia","Eleine","Elena ","Eleonor","Eleonora","Eleutério","Elgar","Eli ","Élia","Elia","Eliab","Eliana","Eliane","Eliano","Elias","Elícia","Elieen","Eliete","Eliezer ","Élin ","Elina","Eline","Élio ","Elioenai ","Elisa","Elisabeta","Elisabete ","Elisabeth ","Elisama","Eliseba","Elisete","Eliseo","Eliseu","Elísia","Elisiário","Elizabeth","Elma","Elmano","Elmar","Elmer","Elmira","Elmo","Eloá ","Elodia","Elódia","Elói","Eloisa","Elpídio","Elsa","Elsinda","Élsio","Élson","Élton","Eluína","Elva","Elvina","Elvino","Elza","Elzeário","Elzo","Ema","Emma","Emmanuel","Emmanuella","Emanuel","Emanuela","Emaús","Emídia","Emídio","Emília","Emiliana","Emo","Encarnação","Eneias","Enes","Engelécia","Engrácio","Énia","Enide","Enilda","Énio","Enoque ","Enrico","Enrique ","Enzo","Éola","Eponina","Ercília","Ercílio","Eric","Erica","Érica","Erick","Erico","Érico","Erik","Erika","Erique","Éris ","Erméria","Ermitério","Ernâni ","Esaú","Esmeralda","Esmeraldo","Esméria","Especiosa","Esperança","Estanislau","Esteban","Estéfana","Estefânia","Estéfano","Estela","Estélio","Ester","Estevam","Esteve","Estêvão","Estrela","Estrelle","Etel ","Étel ","Etelca","Etéria","Étienne","Ettie","Eudo","Eudora","Eufémia","Eugénia","Eugénio","Eularina","Eulógio","Eunice","Eurica","Eurico","Eurídice","Eustácio","Eutália","Eva","Evaldo","Evandra","Evandro","Evangelino","Evangelista ","Evanilde","Evaristo","Evelácio","Evelásio","Evelina","Eveline","Evélio","Evêncio","Everaldo","Everardo","Évila","Expedito","Ezequiel","Ezequiela"};
		Random rand = new Random();
		int qual_nome = rand.nextInt(nomes.length);
		return nomes[qual_nome];
	}

	private String gerarSobrenome(){
		String[] sobrenomes = {"Santos", "Silva", "Santiago", "Leal",
				"Leao", "Oliveira", "Ramos", "Castellani",
				"Vieira", "Alves", "Andrade", "Barbosa",
				"Barros", "Batista", "Borges", "Campos",
				"Cardoso", "Carvalho", "Castro", "Costa",
				"Dias", "Duarte", "Freitas", "Fernandes",
				"Ferreira", "Garcia", "Gomes", "Gonçalves","Lopes","Altoe","Sossai","Agrizzi","Angeli","Ferreira","Braga","da","Silva","Della","Coletta","Zampirolli","Fernandes","Alves","Costalonga","Botteon","Caliman","Oliveira","Zanette","Salvador","Silva","Zandonadi","Pesca","Falqueto","Tosi","da","Costa","Souza","Gomes","Calmon","Pereira","Sossai","detto","Pegorer","Almeida","Jesus","Martins","Balarini","Rodrigues","GonÃ§alves","Pizzol","Moreira","Vieira","Venturim","Bazoni","Filete","Almeida","CorrÃªa","Oliveira","dos","Santos","Falchetto","Barbosa","Breda","Scaramussa","Barros","Marques","Milanez","Travaglia","Calvi","FiÃ³rio","Ribeiro","Cesconeto","Costa","Giobini","Laus","Minatel","Tonon","Brioschi","Nogueira","Cardoso","Dalvi","Lorenzoni","Merenciano","Nielson","Partelli","Ramos","Carvalho","Filetti","Francisco","Lemos","Marangon","Patricio","Pinto","ToÃ¨","Moraes","Duarte","Grand-Pre","Machado","Pires","Soares","Barrazzuol","Cipriano","Faria","Doimo","Justo","Larsen","Lima","Nunes","Pagotto","Passos","Andrade","Bravim","Calliman","Francisca","Santos","Tostes","Ambrozim","Buzon","Dansi","Dias","Garcia","Marin","Miranda","Pola","Suave","Teixeira","Valiatti","Barrazuol","Bergami","Buson","Camata","da","Cunha","Lima","Feu","Gaigher","Gava","Moura","Uliana","Azevedo","Casagrande","Cocco","Freitas","Paula","Luis","Lupino","MÃ¸rk","Perim","SalomÃ£o","Vugarato","Bordin","BrandÃ£o","Caldeira","Cancian","Cesconetto","Correia","Dal","Fior","Melo","Siqueira","Delpupo","Donadello","Giacomeli","Giacomelli","Lopes","Louzada","Mariani","MergÃ¡r","Moscon","Olsen","SartÃ³rio","Spadeto","Valiati","Baldotto","Cunha","Abreu","AraÃºjo","Fusinato","Monteiro","Nicoli","Paresque","PerdigÃ£o","Rocha","Silotti","Souza","Tessaro","Tres","Viana","AraÃºjo","Baptista","Bellon","Bissoli","Brezinski","CÃ´go","Cricco","Carvalho","GuimarÃ£es","Holliday","Jensdatter","Klein","Leite","Leme","Marochio","Pianessoli","Reis","Scaramuzza","Zampirollo","Zanellato","Andersen","Battistella","Bedard","Cansi","Christensen","Crozatti","Agnoi","Andrades","Castro","Nadai"};
		Random rand = new Random();
		int qual_nome = rand.nextInt(sobrenomes.length);
		return sobrenomes[qual_nome];
	}

	private String gerarEndereco(){
		Random rand = new Random();
		String[]ruas={};
		int numero = rand.nextInt(300);
		String[]letras={"-A","-B","-C","-D","-E","-F","-G","-H","-I","-L","-M","-N","-O","-P","-Q","-R","-S","-T","-V","-X","-Z"};
		String[] bairros = {"Acupe","Aeroporto","Águas Claras","Alto da Terezinha","Alto das Pombas","Alto do Cabrito","Alto do Coqueirinho","Amaralina","Areia Branca","Arenoso","Arraial do Retiro","Bairro da Paz","Baixa de Quintas","Barbalho","Barra","Barreiras","Barris","Beiru/Tancredo Neves","Boa Viagem","Boa Vista de Brotas","Boa Vista de São Caetano","Boca da Mata","Boca do Rio","Bom Juá","Bonfim","Brotas","Cabula","Cabula VI","Caixa D’Água","Cajazeiras II","Cajazeiras IV","Cajazeiras V","Cajazeiras VI","Cajazeiras VII","Cajazeiras VIII","Cajazeiras X","Cajazeiras XI","Calabar","Calabetão","Calçada","Caminho das Árvores","Caminho de Areia","Campinas de Pirajá","Canabrava","Candeal","Canela","Capelinha","Cassange","Castelo Branco","Centro","Centro Administrativo da Bahia","Centro Histórico","Chapada do Rio Vermelho","Cidade Nova","Comércio","Cosme de Farias","Costa Azul","Coutos","Curuzu","Dom Avelar","Doron","Engenho Velho da Federação","Engenho Velho de Brotas","Engomadeira","Fazenda Coutos","Fazenda Grande do Retiro","Fazenda Grande I","Fazenda Grande II","Fazenda Grande III","Fazenda Grande IV","Federação","Garcia","Graça","Granjas Rurais Presidente Vargas","IAPI","Ilha de Bom Jesus dos Passos","Ilha de Maré","Ilha dos Frades","Imbuí","Itacaranha","Itaigara","Itapuã","Itinga","Jaguaripe I","Jardim Armação","Jardim Cajazeiras","Jardim das Margaridas","Jardim Nova Esperança","Jardim Santo Inácio","Lapinha","Liberdade","Lobato","Luiz Anselmo","Macaúbas","Mangueira","Marechal Rondon","Mares","Massaranduba","Mata Escura","Matatu","Monte Serrat","Moradas da Lagoa","Mussurunga","Narandiba","Nazaré","Nordeste de Amaralina","Nova Brasília","Nova Constituinte","Nova Esperança","Nova Sussuarana","Novo Horizonte","Novo Marotinho","Ondina","Palestina","Paripe","Patamares","Pau da Lima","Pau Miúdo","Periperi","Pernambués","Pero Vaz","Piatã","Pirajá","Pituaçu","Pituba","Plataforma","Porto Seco Pirajá","Praia Grande","Resgate","Retiro","Ribeira","Rio Sena","Rio Vermelho","Roma","Saboeiro","Santa Cruz","Santa Luzia","Santa Mônica","Santo Agostinho","Santo Antônio","São Caetano","São Cristóvão","São Gonçalo","São João do Cabrito","São Marcos","São Rafael","São Tomé","Saramandaia","Saúde","Sete de Abril","Stella Maris","STIEP","Sussuarana","Tororó","Trobogy","Uruguai","Vale das Pedrinhas","Vale dos Lagos","Valéria","Vila Canária","Vila Laura","Vila Ruy Barbosa/Jardim Cruzeiro","Vitória"};
		String[] cidades = {"Abaira","Abare","Acajutiba","Adustina","Agua Fria","Aiquara","Alagoinhas","Alcobaca","Almadina","Amargosa","Amelia Rodrigues","America Dourada","Anage","Andarai","Andorinha","Angical","Anguera","Antas","Antonio Cardoso","Antonio Goncalves","Apora","Apuarema","Aracas","Aracatu","Araci","Aramari","Arataca","Aratuipe","Aurelino Leal","Baianopolis","Baixa Grande","Banzae","Barra da Estiva","Barra do Choca","Barra do Mendes","Barra do Rocha","Barra","Barreiras","Barro Alto","Barro Preto","Belmonte","Belo Campo","Biritinga","Boa Nova","Boa Vista do Tupim","Bom Jesus da Lapa","Bom Jesus da Serra","Boninal","Bonito","Boquira","Botupora","Brejoes","Brejolandia","Brotas de Macaubas","Brumado","Buerarema","Buritirama","Caatiba","Cabaceiras do Paraguacu","Cachoeira","Cacule","Caem","Caetanos","Caetite","Cafarnaum","Cairu","Caldeirao Grande","Camacan","Camacari","Camamu","Campo Alegre de Lourdes","Campo Formoso","Canapolis","Canarana","Canavieiras","Candeal","Candeias","Candiba","Candido Sales","Cansancao","Canudos","Capela do Alto Alegre","Capim Grosso","Caraibas","Caravelas","Cardeal da Silva","Carinhanha","Casa Nova","Castro Alves","Catolandia","Catu","Caturama","Central","Chorrocho","Cicero Dantas","Cipo","Coaraci","Cocos","Conceicao da Feira","Conceicao do Almeida","Conceicao do Coite","Conceicao do Jacuipe","Conde","Condeuba","Contendas do Sincora","Coracao de Maria","Cordeiros","Coribe","Coronel Joao Sa","Correntina","Cotegipe","Cravolandia","Crisopolis","Cristopolis","Cruz das Almas","Curaca","Dario Meira","Dias d'Avila","Dom Basilio","Dom Macedo Costa","Elisio Medrado","Encruzilhada","Entre Rios","Erico Cardoso","Esplanada","Euclides da Cunha","Eunapolis","Fatima","Feira da Mata","Feira de Santana","Filadelfia","Firmino Alves","Floresta Azul","Formosa do Rio Preto","Gandu","Gaviao","Gentio do Ouro","Gloria","Gongogi","Governador Mangabeira","Guajeru","Guanambi","Guaratinga","Heliopolis","Iacu","Ibiassuce","Ibicarai","Ibicoara","Ibicui","Ibipeba","Ibipitanga","Ibiquera","Ibirapitanga","Ibirapua","Ibirataia","Ibitiara","Ibitita","Ibotirama","Ichu","Igapora","Igrapiuna","Iguai","Ilheus","Inhambupe","Ipecaeta","Ipiau","Ipira","Ipupiara","Irajuba","Iramaia","Iraquara","Irara","Irece","Itabela","Itaberaba","Itabuna","Itacare","Itaete","Itagi","Itagiba","Itagimirim","Itaguacu da Bahia","Itaju do Colonia","Itajuipe","Itamaraju","Itamari","Itambe","Itanagra","Itanhem","Itaparica","Itape","Itapebi","Itapetinga","Itapicuru","Itapitanga","Itaquara","Itarantim","Itatim","Itirucu","Itiuba","Itororo","Ituacu","Itubera","Iuiu","Jaborandi","Jacaraci","Jacobina","Jaguaquara","Jaguarari","Jaguaripe","Jandaira","Jequie","Jeremoabo","Jiquirica","Jitauna","Joao Dourado","Juazeiro","Jucurucu","Jussara","Jussari","Jussiape","Lafaiete Coutinho","Lagoa Real","Laje","Lajedao","Lajedinho","Lajedo do Tabocal","Lamarao","Lapao","Lauro de Freitas","Lencois","Licinio de Almeida","Livramento do Brumado","Macajuba","Macarani","Macaubas","Macurure","Madre de Deus","Maetinga","Maiquinique","Mairi","Malhada de Pedras","Malhada","Manoel Vitorino","Mansidao","Maracas","Maragogipe","Marau","Marcionilio Souza","Mascote","Mata de Sao Joao","Matina","Medeiros Neto","Miguel Calmon","Milagres","Mirangaba","Mirante","Monte Santo","Morpara","Morro do Chapeu","Mortugaba","Mucuge","Mucuri","Mulungu do Morro","Mundo Novo","Muniz Ferreira","Muquem de Sao Francisco","Muritiba","Mutuipe","Nazare","Nilo Pecanha","Nordestina","Nova Canaa","Nova Fatima","Nova Ibia","Nova Itarana","Nova Redencao","Nova Soure","Nova Vicosa","Novo Horizonte","Novo Triunfo","Olindina","Oliveira dos Brejinhos","Ouricangas","Ourolandia","Palmas de Monte Alto","Palmeiras","Paramirim","Paratinga","Paripiranga","Pau Brasil","Paulo Afonso","Pe de Serra","Pedrao","Pedro Alexandre","Piata","Pilao Arcado","Pindai","Pindobacu","Pintadas","Pirai do Norte","Piripa","Piritiba","Planaltino","Planalto","Pocoes","Pojuca","Ponto Novo","Porto Seguro","Potiragua","Prado","Presidente Dutra","Presidente Janio Quadros","Presidente Tancredo Neves","Queimadas","Quijingue","Quixabeira","Rafael Jambeiro","Remanso","Retirolandia","Riachao das Neves","Riachao do Jacuipe","Riacho de Santana","Ribeira do Amparo","Ribeira do Pombal","Ribeirao do Largo","Rio Real","Rio de Contas","Rio do Antonio","Rio do Pires","Rodelas","Ruy Barbosa","Salinas da Margarida","Salvador","Santa Barbara","Santa Brigida","Santa Cruz Cabralia","Santa Cruz da Vitoria","Santa Ines","Santa Luzia","Santa Maria da Vitoria","Santa Rita de Cassia","Santa Teresinha","Santaluz","Santana","Santanopolis","Santo Amaro","Santo Antonio de Jesus","Santo Estevao","Sao Desiderio","Sao Domingos","Sao Felipe","Sao Felix do Coribe","Sao Felix","Sao Francisco do Conde","Sao Gabriel","Sao Goncalo dos Campos","Sao Jose da Vitoria","Sao Jose do Jacuipe","Sao Miguel das Matas","Sao Sebastiao do Passe","Sapeacu","Satiro Dias","Saubara","Saude","Seabra","Sebastiao Laranjeiras","Senhor do Bonfim","Sento Se","Serra Dourada","Serra Preta","Serra do Ramalho","Serrinha","Serrolandia","Simoes Filho","Sitio do Mato","Sitio do Quinto","Sobradinho","Souto Soares","Tabocas do Brejo Velho","Tanhacu","Tanque Novo","Tanquinho","Taperoa","Tapiramuta","Teixeira de Freitas","Teodoro Sampaio","Teofilandia","Teolandia","Terra Nova","Tremedal","Tucano","Uaua","Ubaira","Ubaitaba","Ubata","Uibai","Umburanas","Una","Urandi","Urucuca","Utinga","Valenca","Valente","Varzea Nova","Varzea da Roca","Varzea do Poco","Varzedo","Vera Cruz","Vereda","Vitoria da Conquista","Wagner","Wanderley","Wenceslau Guimaraes","Xique-Xique"};
		String[] estados = {"Acre","Alagoas","Amapá","Amazonas","Bahia","Ceará","Distrito Federal","Espírito Santo","Goiás","Maranhão","Mato Grosso","Mato Grosso do Sul","Minas Gerais","Pará","Paraíba","Paraná","Pernambuco","Piauí","Rio de Janeiro","Rio Grande do Norte","Rio Grande do Sul","Rondônia","Roraima","Santa Catarina","São Paulo","Sergipe","Tocantins"};

		int qual_letra = rand.nextInt(letras.length);
		int qual_bairro = rand.nextInt(bairros.length);
		int qual_cidade = rand.nextInt(cidades.length);
		int qual_estado = rand.nextInt(estados.length);
		String rua = bairros[qual_bairro] + " " + cidades[qual_cidade];
		String cep = "";
		for(int i=0;i<8;i++){
			cep = cep + String.valueOf(rand.nextInt(9));
		}
		return rua + "," + numero + letras[qual_letra] + "," + bairros[qual_bairro] + "-" + cep + "-" + cidades[qual_cidade] + "," + estados[qual_estado];
	}

	private String gerarLocalTrabalho(){
		String[] localTrabalho = {"UNEB", "Horizon CI", "Space Rocket", "ATENTO",
				"TCE-BA", "TJ-BA", "MP-BA", "TecnoTrends",
				"Avansys", "Inove Serviços", "Policia Militar da bahia",
				"fapesb", "Bigpoint", "Riot Games", "Brasilgas",
				"Coelba", "Embasa", "Prefeitura de Salvador", "Costa Produções",
				"Simaw Tecnologias", "Elevii Soluções", "Fortalezza Biscoitos", "Kondzilla Produções",
				"Oi fibra", "Tim Live", "M Estetica Studio De Beleza", "Hinodê","Avon","Caratch","Caromni","Stepegg","Caraipi","Netelectra","Cafesea","Cafefire","Woodtap","Reelectra","Cafejar","Cafemirror","Electra","TheCar","Group","Bestofstep","Enginecafe","Nuelectra","Carer","Softdude","Woodcell","Targetwood","Cardecu","Stephq","Sweetwiki","Hubwood","Wowstep","Rreget","Telesweet","Woodoffers","Steploop","Carroch","Cabinsoft","Electraall","Carceag","Ranchsoft","Softjunky","Woodrace","Titanicpower","Trycup","Goldalpha","Zippyhigh","Leaderhigh","Herowild","Timeneo","Vipever","Funvita","Nextwavefashion","Safetyvita","Vitaprofessional","Surfacefashion","Hugecake","Morenova","Joyprofessional","Availgol","Primalteam","Winnerelite","Rightelite","Meelite","Dayneo","Novagenius","Onlynova"};

		Random rand = new Random();
		int qual_nome = rand.nextInt(localTrabalho.length);
		return localTrabalho[qual_nome];
	}

	private String gerarTelefone(){
		Random rand = new Random();
		String ddd = "(0"+ String.valueOf(aleatoriar(40,90)) + ")";
		String telefone = "";
		for(int i = 0; i<8;i++){
			telefone = telefone + String.valueOf(rand.nextInt(9));
		}

		return ddd + telefone;
	}

	public static int aleatoriar(int minimo, int maximo) {
		Random random = new Random();
		return random.nextInt((maximo - minimo) + 1) + minimo;
	}

	public int criaMenuPrincipal(){
		int opcao;
		System.out.println("Menu de Opcoes:");
		System.out.println("-------------------");
		System.out.println("1. Adicionar Usuário");
		System.out.println("2. Preencher banco");
		System.out.println("-------------------");
		opcao = Integer.parseInt(sc.nextLine());
		return opcao;
	}

	public static void main(String args[]){
		try {
			ColecaoArtigo cp = new ColecaoArtigo();
			int opcao=8;
			while ((opcao = cp.criaMenuPrincipal())!=0){
				if(opcao == ColecaoArtigo.ADICIONAR){
					cp.adicionarUsuarioCmd();
				}else if(opcao == ColecaoArtigo.PREENCHER){
					cp.preencherBanco(cp);
				}else{
					System.out.println("Escolha uma opcao correta.");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
}