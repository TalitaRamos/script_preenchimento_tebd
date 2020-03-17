package script_preenchimento.hibernate;
import java.util.*;

public class ColecaoArtigo {
	public static final int ADICIONAR = 1;
	public static final int PREENCHER = 2;
	Scanner sc = new Scanner(System.in);

	private void preencherBanco(ColecaoArtigo cp){
		try {
			//ADICIONAR USUÁRIOS
		    for(int i=0;i<10000;i++){
                cp.AdicionarUsuario();
            }
			UsuarioController con = new UsuarioController();
		    List<Usuario>usuarioList = con.findAll();
		    //ADICONAR CARTÕES
		    for(int j=0;j<usuarioList.size();j++){
				Cartao cartao = cp.AdicionarCartao(usuarioList.get(j));
				con.AdicionarUsuarioCartao(usuarioList.get(j),cartao,PREENCHER);
			}
		    con.fechar();
		    //ADICONAR ARTIGOS
		    for(int l=0;l<3000;l++){
				cp.AdicionarArtigo();
			}
			//ADICONAR REVISAO
			cp.AdicionarRevisao();
		    //CALCULAR MEDIA
			cp.calcularMedia();
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

		UsuarioController con = new UsuarioController();
		Usuario a = new Usuario(usuario_nome,usuario_endereco,usuario_telefone,usuario_email,usuario_local_trabalho,usuario_is_revisor,0);
		con.salvarUpdateUsuario(a);
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
		con.fechar();
	}

	public void AdicionarArtigo() throws Exception {
		Random rand = new Random();
		ArtigoController artCon = new ArtigoController();
		UsuarioController con = new UsuarioController();

		String[] artigo_titulo = {"A PERDA DO AMOR É IGUAL À PERDA DA MORTE: UM ESTUDO SOBRE O AMOR EM CAIO FERNANDO ABREU","A ABORDAGEM QUÍMICA NO ENEM","A ANDRAGOGIA E O LETRAMENTO: IMPLICAÇÕES PARA A EDUCAÇÃO DE JOVENS E ADULTOS","A CONCEPÇÃO DE ÁTOMO NA QUÍMICA NA FÍSICA E NA BIOLOGIA: UMA VISÃO MULTIDISCIPLINAR","A IDENTIDADE DE LAGARTO CONTADA POR UM MEMORIALISTA","A IMPORTÂNCIA DA ESTRATÉGIA DE MARKETING","NO COMÉRCIO VESTUÁRIO DA CIDADE DE GUARABIRA-PB","A IMPORTÂNCIA DO ENSINO DE QUÍMICA NA EDUCAÇÃO DE JOVENS E ADULTOS: QUÍMICA PRA QUE TE QUERO?","A IMPORTÂNCIA DO PROGRAMA PIBID DO IFRN-APODI NO DESENVOLVIMENTO E USO DE","NOVAS METODOLOGIAS NO ENSINO DE QUÍMICA","A PREPARAÇÃO DE ALUNOS DA ESCOLA PÚBLICA PARA O PROCESSO SELETIVO DO IFRN: PROJETO DE EXTENSÃO IF + PÚBLICO","A RELAÇÃO DA QUÍMICA COM A CULTURA INDÍGENA","A TRAJETÓRIA MILITANTE DE ANTONIO BERNARDO CANELLAS NA CIDADE DE VIÇOSA EM ALAGOAS NA SEGUNDA DÉCADA DO SÉCULO XX (1916-1917)","A UTILIZAÇÃO DA GARRAFA PET COMO","FERRAMENTA DIDÁTICA NA COMPREENSÃO DE FENÔMENOS QUÍMICOS E FÍSICOS DA NATUREZA","A UTILIZAÇÃO DO JOGO DE TABULEIRO COMO PROPOSTA DE RECURSO DIDÁTICO-PEDAGÓGICO NO ENSINO DE GEOGRAFIA PARA ALUNOS COM DEFICIÊNCIA INTELECTUAL NA PERSPECTIVA DA","EDUCAÇÃO INCLUSIVA","ACIDENTE DE TRAJETO: ANALISE MULTIVARIADA DO FENÔMENO NO NORDESTE BRASILEIRO","ADIÇÃO DE FITASE A DIFERENTES CONCENTRAÇÕES DE CÁLCIO E FÓSFORO NO CRESCIMENTO DE FRANGOS","ALGUMAS IMPLICAÇÕES DA IDENTIFICAÇÃO DE TIPAGENS SANGUÍNEAS COM O USO DE SOROS AGLUTINÍNICOS ANTI-A ANTI-B E ANTI-RH (ANTI-D)","ALIMENTOS PARA LACTENTES E CRIANÇAS DE PRIMEIRA INFÂNCIA: UMA AVALIAÇÃO DA ROTULAGEM","ANÁLISE DA ACESSIBILIDADE PARA DEFICIENTES NO SÍTIO DO IFRN","ANÁLISE DE EMISSÃO DE FUMAÇA PRETA EM MACAPÁ/AP","ANÁLISE DE SISTEMA DE ATERRAMENTO DE LDS","69KV COM ESTRUTURAS DE CONCRETO SOB DESCARGAS ATMOSFÉRICAS","ANÁLISE DO CURSO TÉCNICO EM CONTABILIDADE DO IFPI EM CONSONÂNCIA COM O CATÁLOGO NACIONAL DE CURSOS TÉCNICOS","ANÁLISE DO GRAU DE SATISFAÇÃO DOS CLIENTES","DA LANCHONETE E PIZZARIA ROMA DE SANTO ANTÔNIO/RN","ANÁLISE DO ÍNDICE DE SUSTENTABILIDADE DO MUNICÍPIO DE QUIXERAMOBIM-CE","ANÁLISE DO LIVRO DIDÁTICO DE BIOLOGIA DO ENSINO MÉDIO SOBRE BACTÉRIAS","ANÁLISE ECONÔMICA DA PROPRIEDADE LEITEIRA MODAL DO ESTADO DO ACRE","ANÁLISE POR HPLC DE EXTRATOS PRODUZIDOS POR FUNGOS ENDOFÍTICOS ISOLADOS DE CURCUMA LONGA","APORTE DA PRÁTICA EXPERIMENTAL PARA O","PROCESSO DE ENSINO APRENDIZADO NA DISCIPLINA DE FÍSICA","ARMAZENADOR DE DRÁGEAS COM REGISTRO DE USO","ARTIGO_REAPROVEITAMENTO E MODELAGEM DE PAPEL NA CONSTRUÇÃO DE FORMAS GEOMETRICAS","AS IMAGENS NO LIVRO DE SOCIOLOGIA: UMA ANÁLISE SEMIÓTICA DAS RELAÇÕES ENTRE IMAGEM E TEXTO EM LIVRO DIDÁTICO","AS LIÇÕES DA CARTA SOBRE A FELICIDADE DE EPICURO PARA SUPERAR AS SEDUÇÕES DO CONSUMISMO CONTEMPORÂNEO","AS NOVAS TECNOLOGIAS DA INFORMAÇÃO E DA COMUNICAÇÃO NAS PRÁTICAS PEDAGÓGICAS DA EDUCAÇÃO A DISTÂNCIA","AVALIAÇÃO DA ATIVIDADE LIPÁSICA DE SEMENTES NA REAÇÃO DE HIDRÓLISE DO AZEITE DE OLIVA","AVALIAÇÃO DA EFICIÊNCIA DA GALVANIZAÇÃO COMO PROTEÇÃO ANTICORROSIVA DE COMPONENTES","AVALIAÇÃO DA FAUNA FÚNGICA DO TAMBAQUI (COLOSSOMA MACROPOMUM) COMERCIALIZADO NO MERCADO MUNICIPAL DE ARIQUEMES – RO","AVALIAÇÃO DE ALTURA DIÂMETRO E MATÉRIA SECA DE MUDAS DE IPÊ-AMARELO SOB OMISSÃO DE NUTRIENTES","CARACTERIZAÇÃO DE ARGILA PLÁSTICA PARA CONFECÇÃO DE PEÇAS DE CERÂMICA VERMELHA: ESTRUTURA E PROPRIEDADES DE PRODUTOS CERÂMICOS","CARACTERIZAÇÃO E BIODEGRADABILIDADE AERÓBIA DOS ESGOTOS DA ETE LIBERDADE","CARACTERIZAÇÃO FÍSICO-QUÍMICA E ANÁLISE SENSORIAL DO BISCOITO TIPO COOKIE COM A ADIÇÃO PARCIAL DA FARINHA DA CASCA DA MELANCIA","CITI – COMPILADOR INTERATIVO COM TRADUTOR INTEGRADO: UM AMBIENTE PARA AUXÍLIO AO ENSINO-APRENDIZAGEM DE PROGRAMAÇÃO PARA","ALUNOS INICIANTES","CLASSIFICAÇÃO DA QUALIDADE DE ÁGUA PARA IRRIGAÇÃO DO PERÍMETRO IRRIGADO TABULEIROS DE RUSSAS - CEARÁ","COMPARAÇÃO DA EFICIÊNCIA DAS HIDRÓLISES ÁCIDAS EM BATATAS DOCE E INGLESA","CONFECÇÃO DE PROTÓTIPOS DE BAIXO CUSTO: GERADOR ELÉTRICO","CONFLITOS SOCIOAMBIENTAIS E INTERRUPÇÃO DO SISTEMA DE ABASTECIMENTO DE ÁGUA: UM ESTUDO DE CASO NO MUNICÍPIO DE PRINCESA ISABEL/PB","CONSTRUÇÃO DE UMA ESTUFA MICROCONTROLADA","DECODIFICAÇÃO DE CÓDIGOS CONVOLUCIONAIS EM PLATAFORMA FPGA BASEADA NAS TRELIÇAS CONVENCIONAL E MÍNIMA","DESAFIOS PARA O FUTURO DA JUVENTUDE: LEVANTAMENTO SOBRE A INSERÇÃO DOS JOVENS EGRESSOS DO CURSO DE LICENCIATURA EM EDUCAÇÃO FÍSICA DO IFRR NO MUNDO DO","TRABALHO.","DESENVOLVENDO APLICAÇÕES LÚDICAS PARA O ENSINO DE QUÍMICA: UMA NOVA ESTRATÉGICA DE","ENSINO","DESENVOLVIMENTO DE KITS DIDÁTICOS DE BAIXO CUSTO MODULARES PARA O APRENDIZADO DE ELETRÔNICA DIGITAL","DESENVOLVIMENTO DE PROJETOS INTERDISCIPLINARES COM A TEMÁTICA AMBIENTAL NAS ESCOLAS MUNICIPAIS DE ENSINO FUNDAMENTAL II DO MUNICÍPIO DE","GARANHUNS/PE","DESENVOLVIMENTO DE UM VEÍCULO AÉREO NÃO TRIPULADO COM SISTEMA DE NAVEGAÇÃO MICROCONTROLADO","DESENVOLVIMENTO E ANÁLISE DE CONCENTRADORES TIPO V-TROUGH","DESTINAÇÃO DE RESÍDUOS SÓLIDOS DE UMA COMUNIDADE NA ZONA RURAL DE XAPURI - AC","DIAGNÓSTICO SOBRE A UTILIZAÇÃO DOS LABORATÓRIOS DE MATEMÁTICA E FÍSICA NAS ESCOLAS DE ENSINO MÉDIO DA REDE ESTADUAL DE ENSINO DO ESTADO DO MARANHÃO NA UNIDADE REGIONAL DE EDUCAÇÃO DE SÃO JOÃO","DOS PATOS","DIFICULDADES DOS ALUNOS NA DISCIPLINA DE QUÍMICA NAS ESCOLAS DO ENSINO MÉDIO DO MUNICÍPIO DE PEDRA BRANCA-CE.","DIFUSÃO DE ATIVIDADES EXTENSIONISTAS: RELATO DO USO DO AUDIOVISUAL NO CRPNM/IFPB","/CABEDELO","DISCUSSÕES ACERCA DO NOVO CÓDIGO FLORESTAL BRASILEIRO","DISPONIBILIZAÇÃO DE DADOS GEOESPACIAIS ATRAVÉS DO SOFTWARE WEB I3GEO GEOPROCESSADOS A PARTIR DE TÉCNICAS DE SENSORIAMENTO REMOTO UTILIZADAS PARA AVALIAR O AVANÇO DA PRODUÇÃO DE GRÃOS E CONSEQUENTES IMPACTOS SOCIOAMBIENTAIS","CAUSADOS AO CERRADO PIAUIENSE.","DMITRY: UMA PROPOSTA DE ARQUITETURA DE SOFTWARE PARA GESTÃO DE PROJETOS","EDUCACIONAIS","EDUCAÇÃO AMBIENTAL E CIDADANIA: FERRAMENTAS PARA REDUZIR AS DIFERENÇAS SOCIOAMBIENTAIS EM DOIS BAIRROS DO","MUNICÍPIO DE AÇAILÂNDIA-MA","EDUCAÇÃO SEXUAL: ESTRATÉGIAS PEDAGÓGICAS PARA GRADUANDOS EM CIÊNCIAS BIOLÓGICAS","EFEITO DA TEMPERATURA E DA LUZ SOBRE A GERMINAÇÃO DE MOGNO (SWIETENIA MACROPHYLLA)","EFICIÊNCIA NA SALA DE AULA: O PROCESSO DE REPRESENTAÇÃO DE TURMA NOS CURSOS TÉCNICOS INTEGRADOS","ELÉTRONS CORRELACIONADOS EM CLUSTERS QUADRADOS","ESTUDO COMPARATIVO DA RESISTÊNCIA À COMPRESSÃO DO CONCRETO E DO SLUMP TEST COLETADO EM TRÊS ETAPAS DISTINTAS DO CAMINHÃO BETONEIRA","ESTUDO DA VIABILIDADE DO APROVEITAMENTO DE ÁGUAS PLUVIAS NO IFRN CAMPUS NATAL- CENTRAL","ESTUDO DO COMPORTAMENTO DE IGUANA IGUANA EM ÁREA URBANA E ÁREA DE MATA CILIAR NA CIDADE DE FLORIANO-PI.","ESTUDO DOS SINAIS DE PONTUAÇÃO EM MATERIAL DIDÁTICO DE LÍNGUA PORTUGUESA DO ENSINO FUNDAMENTAL UMA ANÁLISE CRÍTICA","ESVERDEANDO A PRODUÇÃO DE PAVIMENTOS INTERTRAVADOS POR MEIO DO PROCESSO DE LOGÍSTICA REVERSA","EXPERIMENTANDO A QUÍMICA DESDE AS SÉRIES INICIAIS DO ENSINO FUNDAMENTAL","EXPLORANDO A FÍSICA EM EXPERIMENTOS DE CIÊNCIA NUM PROJETO DE EDUCAÇÃO NÃO FORMAL DO SUL DA BAHIA","FÍSICA NO 9° ANO DO ENSINO FUNDAMENTAL: UM ESTUDO DA METODOLOGIA UTILIZADA NA ESCOLA CENTRO DE ENSINO DOUTOR PAULO RAMOS","IDENTIFICAÇÃO MINERALÓGICA DE DIFRATOGRAMA DE UMA AMOSTRA DE CAULIM","IMPLANTAÇÃO DE BICICLETÁRIOS NOS CAMPI DO IFBA: VIABILIDADE TÉCNICA E FINANCEIRA","IMPLANTAÇÃO DE UMA COLEÇÃO DIDÁTICA DE INVERTEBRADOS MARINHOS NO IFPE/CAMPUS CARUARU.","IMPLANTAÇÃO DO PROGRAMA 5S EM UMA COORDENAÇÃO DE UMA INSTITUIÇÃO DE ENSINO","IMPORTÂNCIA DO CBQ PARA O BRASIL","INDICADORES DA QUALIDADE VISUAL DO SOLO EM DIFERENTES SISTEMAS DE USO NO SEMIÁRIDO PARAIBANO","INFERÊNCIA INDUTIVA: MÉTODOS DE MILL E SUA APLICAÇÃO NAS CIÊNCIAS SEGUNDO IRVING COPI","INFLUÊNCIA DA FARINHA DO CAROÇO DE AÇAÍ NOS PARÂMETROS MORFOMÉTRICOS DOS INTESTINOS DE TAMBAQUI (COLOSSOMA","MACROPOMUM)","INOVAÇÃO NA APRENDIZAGEM DE MATEMÁTICA MEDIANTE O USO DE JOGOS COOPERATIVOS","INTERFACES ENTRE A SÉRIE PERCY JACKSON E OS OLIMPIANOS DE RICKY RIORDAN E A MITOLOGIA GREGA: APROXIMAÇÕES/DISTANCIAMENTOS NARRATIVOS E RELEVÂNCIA À POPULARIZAÇÃO DA CULTURA ANTIGA","LABORATÓRIO DE GEOMETRIA ESPACIAL COM MATERIAL RECICLÁVEL.","LAGOA DOS INDIOS: UMA EXPECTATIVA QUILOMBOLA?","MANEJO E QUALIDADE DO ÓLEO DE ANDIROBA EXTRAIDO POR PRENSA","MÁQUINA TERMODINÂMICA: UMA CONSTRUÇÃO ARTESANAL","MARCAS DE AUTORIA EM TEXTOS DE ALUNOS DO 7º E 8º ANOS DO ENSINO FUNDAMENTAL A PONTUAÇÃO NO GÊNERO MEMÓRIAS LITERÁRIAS","MATEMÁTICA APLICADA NA LUZ E FOTOGRAFIA","NOVO CÓDIGO FLORESTAL BRASILEIRO: AVANÇOS E RETROCESSOS DE UM DICOTÔMICO DEBATE","O ENEM DE 1998 A 2008: AUSÊNCIA E ÊNFASE DOS GÊNEROS ARTÍSTICOS NAS QUESTÕES DE ARTE","O ENEM DE 2009 A 2014: ÊNFASE DOS GÊNEROS ARTÍSTICOS E DAS QUESTÕES COM ARTE","O GEOGEBRA COMO INSTRUMENTO DE ENSINO DA MATEMÁTICA","O NOVO PAPEL DO JORNALISTA EM TEMPOS DE REDES SOCIAIS","O PASSADO VALE OURO! UMA EXPERIÊNCIA EM","ORIENTAÇÃO NA OLIMPÍADA NACIONAL EM HISTÓRIA DO BRASIL","O TÓPICO DISCURSIVO NA INTERAÇÃO QUASE MEDIADA: UMA ANÁLISE CONVERSACIONAL DO PROGRAMA RODA VIVA","O(S) SENTIDO(S) DA GUERRA ENTRE A ANTIGUIDADE E A MODERNIDADE OCIDENTAL: UMA ANÁLISE COMPARATIVA DAS MOTIVAÇÕES GUERREIRAS NOS CAMPOS DE BATALHA DE TRÓIA","E DA SEGUNDA GUERRA MUNDIAL","OBSERVAÇÃO DAS CONDIÇÕES HIGIÊNICA DOS PRODUTOS COMERCIALIZADOS NA FEIRA DO PRODUTOR DO MUNICÍPIO DE BOA VISTA-RR","OFICINAS DE MECÂNICA PARA AS ESCOLAS PÚBLICAS DE SÃO GONÇALO DO AMARANTE/RN","ORGANIZAÇÃO DO ESPAÇO ESCOLAR EM SALAS- AMBIENTE: ESTUDO EXPLORATÓRIO COM ALUNOS DO","OS DETERMINANTES DA DENGUE NO CONTEXTO AMAZÔNICO: UMA VISÃO GEOGRÁFICA DO AMBIENTE DA DOENÇA NO ACRE","OS ENTRAVES NO ENSINO DE GENÉTICA COM ALUNOS DO ENSINO MÉDIO DE UMA ESCOLA PÚBLICA DA CIDADE DE MANAUS","PARA INICIO DE CONVERSA: REDUÇÃO DA MAIORIDADE PENAL POLITICAS PÚBLICAS E EDUCAÇÃO.","PERCEPÇÃO AMBIENTAL DE FREQUENTADORES DO BOSQUE MUNICIPAL EM URUPÁ /RO BRASIL","PERCEPÇÃO AMBIENTAL DOS ALUNOS DO ENSINO MÉDIO DO IFMA - CAMPUS CODÓ SOBRE A DESMISTIFICAÇÃO DE ANIMAIS PEÇONHENTOS E","VENENOSOS","POTENCIAL ALELOPÁTICO DO EXTRATO AQUOSO DE JATROPHA GOSSYPIIFOLIA L. NA GERMINAÇÃO DE LACTUCA SATIVA L.","PROFESSOR DE MATEMÁTICA: UMA ESTRATÉGIA DE ENSINO NA TRANSFORMAÇÃO DO SEU FAZER ENSINAR","PROPOSTA DE USO DA PIEZOELETRICIDADE PARA A IMPLEMENTAÇÃO DE SISTEMAS ENERGÉTICOS ALTERNATIVOS","PROPRIEDADE INTELECTUAL E TRANSFERÊNCIA DE TECNOLOGIA NO CENÁRIO DA LEI DE INOVAÇÃO (LEI N° 10.973/04) E DA EMENDA CONSTITUCIONAL 85/2015","PROSPECÇÃO TECNOLÓGICA DO USO DO CAJUEIRO (ANACARDIUM OCCIDENTALE L.) COMO FITOTERÁPICO","PROTÓTIPO DIDÁTICO PARA ELETRÔNICA DIGITAL","QUÍMICA VERSUS SEGURANÇA NO TRABALHO: IDENTIFICANDO OS RISCOS NO LABORATÓRIO DE QUÍMICA","RECUPERAÇÃO DA NASCENTE DO CÓRREGO DAS TÁBUAS EM VAZANTE - MG","REFLEXÕES SOBRE AS CONTRIBUIÇÕES DA COMPUTAÇÃO MÓVEL À APRENDIZAGEM AUTÔNOMA DE LÍNGUA INGLESA","REGISTRO DA MEMÓRIA MUSICAL MONTEIRENSE: ZABÉ DA LOCA","RESÍDUOS SÓLIDOS: SABERES E PRÁTICAS DOS DISCIENTES DO CURSO TÉCNICO EM ELETROELETRÔNICA SUBSEQUENTE DO IFPE","CAMPUS GARANHUNS","SERRAPILHEIRA ACUMULADA EM DIFERENTES ESTÁGIOS SUCESSIONAIS DA CAATINGA NO SEMIÁRIDO DA PARAÍBA","SIMULAÇÕES NUMÉRICAS PARA ILUMINAÇÃO E VENTILAÇÃO NATURAIS COM SOFTWARE BIM","SÍNTESE DE BIODIESEL A PARTIR DE ÓLEOS RESIDUAIS","SÍNTESE DE CANDIDATOS A FOTOPROTETORES ORGÂNICOS UTILIZANDO REAÇÕES DO TIPO CONDENSAÇÃO ALDÓLICA","TABULEIRO ORGÂNICO – MINA DE","HIDROCARBONETOS: UMA PROPOSTA LÚDICA PARA O ENSINO INTRODUTÓRIO A CADEIAS CARBÔNICAS.","TECNOLOGIAS SOCIAIS COMO ELEMENTOS DE TRABALHO PARA O ENSINO DE QUÍMICA ANALÍTICA E INSTRUMENTAL","TECNOLOGIAS SOCIAIS NO SEMIÁRIDO ALAGOANO","Titulo","TRÁFICO DE ANIMAIS SILVESTRES NO SUDOESTE DA BAHIA CAMINHOS E DESCAMINHOS!","TRATAMENTO DE ÁGUAS RESIDUAIS UTILIZANDO","ARGILA MODIFICADA COM MORINGA E NANOPARTÍCULAS DE PRATA","UM PANORAMA ACERCA DOS ESPAÇOS DE EDUCAÇÃO NÃO FORMAL NA MICRORREGIÃO DE ILHÉUS-ITABUNA","UMA PROPOSTA DE SOLUÇÃO OFFLINE UTILIZANDO DISPOSITIVOS ANDROID PARA DIÁRIOS ACADÊMICOS DO IFRN","USO DE PLANTAS MEDICINAIS PELA POPULAÇAO DO SETOR 4 NO MUNICÍPIO DE VALE DO PARAISO","RONDÔNIA BRASIL","UTILIZAÇÃO DE CASCAS DE FRUTAS COMO BIOCATALISADORES NA REAÇÃO NA PRODUÇÃO DE","BIOSSURFACTANTES","UTILIZAÇÃO DE EMBALAGENS TETRA PAK COMO PAINÉIS PARA MELHORIA DO CONFORTO TÉRMICO EM RESIDÊNCIAS","VIABILIZAÇÃO DA UTILIZAÇÃO DOS RESÍDUOS DO VIDRO NA CONFECÇÃO DE TIJOLOS DE SOLO CIMENTO"};
		int qual_artigo = rand.nextInt(artigo_titulo.length);
		String artigo_resumo = " É claro que a revolução dos costumes promove a alavancagem do remanejamento dos quadros funcionais. Nunca é demais lembrar o peso e o significado destes problemas, uma vez que a complexidade dos estudos efetuados não pode mais se dissociar das diretrizes de desenvolvimento para o futuro. O empenho em analisar o julgamento imparcial das eventualidades assume importantes posições no estabelecimento do sistema de participação geral.";
		String artigo_arquivo = "c:/pagina/biblioteca/fpdf/font/artigo_"+artigo_titulo[qual_artigo].trim().replaceAll(" ", "_")+".pdf";
		int artigo_qtd_revisores = (aleatoriar(1,5));
		int artigo_qtd_autores = (aleatoriar(1,6));
		double artigo_media = 0;
		Artigo artigo = new Artigo(artigo_titulo[qual_artigo],artigo_resumo,artigo_arquivo,artigo_qtd_revisores, (float) artigo_media);

		List<ArtigoAutor> artigoAutorList = new ArrayList<>();
		List<Usuario>usuarioList = con.findAll();
		boolean existe = false;
		//Sortear quais usuários seram autores do artigo
		//Não permitir que um autor seja autor de um mesmo artigo mais de uma vez
		for(int i=0;i<artigo_qtd_autores;i++){
			int qual_autor = rand.nextInt(usuarioList.size());
			ArtigoAutor artigoAutor = new ArtigoAutor();
			for(int l=0;l<artigoAutorList.size();l++){
				if(artigoAutorList.get(l).getUsuario().getUsuario_id() == usuarioList.get(qual_autor).getUsuario_id()){
					existe = true;
				}
			}
			if(!existe){
				//adicionar o artigo ao autor_artigo
				artigoAutor.setArtigo(artigo);
				artigoAutor.setUsuario(usuarioList.get(qual_autor));
				artigoAutor.setEmail_autor(usuarioList.get(qual_autor).getUsuario_email());
				artigoAutor.setArtigo(artigo);
				artigoAutorList.add(artigoAutor);
				usuarioList.get(qual_autor).setUsuario_is_autor(1);
				con.salvarUpdateUsuario(usuarioList.get(qual_autor));
			}
		}
		//---------------
		//Adicionar a lista de autores ao artigo
		artigo.setArtigoAutorList(artigoAutorList);
		artCon.salvarArtigo(artigo);
		artCon.fechar();
		con.fechar();
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
		carCon.fechar();
		return cartao;
	}

	public void AdicionarRevisao() throws Exception{
		Random rand = new Random();
		ArtigoController artigoController = new ArtigoController();
		UsuarioController usuarioController = new UsuarioController();
		RevisaoController revisaoController = new RevisaoController();

        List<Artigo> artigoList = artigoController.findAll();
		List<Usuario> usuarioList = usuarioController.findUsuarioRevisor();
		List<Usuario> revisores = new ArrayList<>();

		try {
			for(int i=0;i<artigoList.size();i++){
			    if(artigoList.get(i).getRevisaoList().size()<=0){
                    int quant_revisores = artigoList.get(i).getArtigo_qtd_revisores();
                    float media = 0;
                    for(int j=0;j<quant_revisores;j++){
                        Revisao revisao = gerarRevisao();
                        media = media + revisao.getRevisao_nota();
                        int qual_revisor = rand.nextInt(usuarioList.size());
                        if(revisores.contains(usuarioList.get(qual_revisor))){
                            qual_revisor = rand.nextInt(usuarioList.size());
                        }
                        revisao.setUsuario(usuarioList.get(qual_revisor));
                        revisao.setArtigo(artigoList.get(i));
                        usuarioList.get(qual_revisor).setRevisao(revisao);
                        revisores.add(usuarioList.get(qual_revisor));
                        usuarioController.salvarUpdateUsuario(usuarioList.get(qual_revisor));
                    }
                }
				revisores.clear();
			}
			revisaoController.fechar();
			artigoController.fechar();
			usuarioController.fechar();
		}catch (Exception e){
			e.printStackTrace();
		}
    }

    public void calcularMedia() throws Exception{
	    ArtigoController artigoController = new ArtigoController();
	    RevisaoController revisaoController = new RevisaoController();

	    List<Artigo> artigoList = artigoController.findAll();
	    for(int i=0;i<artigoList.size();i++){
			List<Revisao> revisaoList = revisaoController.findRevisaoByArtigo(artigoList.get(i).artigo_id);
			float media = 0;
			for(int j=0;j<revisaoList.size();j++){
				media = media + revisaoList.get(j).revisao_nota;
			}
			media = media/artigoList.get(i).getArtigo_qtd_revisores();
			artigoList.get(i).setArtigo_media(media);
			artigoController.atualizarArtigo(artigoList.get(i));
		}
	    artigoList.clear();

        artigoController.fechar();
        revisaoController.fechar();
	}
    private Revisao gerarRevisao(){
		Random rand = new Random();
		String palavras[]={"barraca","barriga","burro","cachorro","carro","churrasco","corrida","corrupto","errado","erro","ferrado","ferradura","ferro","garra","garrafa","gorro","horrível","irritado","jarra","serra","serrote","sorriso","terremoto","torre","bateria","cadeira","camarão","coleira","coroa","faqueiro","feira","geladeira","gorila","jacaré","lírio","madeira","muro","pera","periquito","picareta","pirata","pirueta","tabuleiro","tubarão","zero","armário","árvore","barba","barbatana","barco","borboleta","calor","carteira","cartola","catorze","cobertor","colar","corda","formiga","garfo","guardanapo","harpa","margarida","martelo","partir","porta","ralador","revólver","sorvete","tartaruga","torneira","torta","urso","verdade","verde","alegre","braço","bravo","brinco","bruxa","bruxaria","cravo","creme","crocodilo","cruzado","dragão","estrela","fruta","grande","gravata","graveto","grilo","igreja","lágrima","livro","madrugada","pedra","praia","prato","prédio","prego","primavera","refresco","trela","três","trevo","truque","zebra","assado","assadura","assobio","massa","missa","nosso","osso","passado","passagem","pássaro","passeio","passo","pêssego","pessoa","pressa","sessenta","sossegado","sossego","tosse","tossir","vassoura","capacete","cebola","cedo","cego","ceia","cena","cenoura","cereja","cesta","cidade","cigano","cigarro","cinema","circo","cisme","hélice","Lúcia","piscina","avião","balão","botão","camarão","caminhão","cidadão","dragão","feijão","fogão","irmão","leão","limão","mamão","melão","pavão","pião","televisão","tubarão","algema","colégio","frigideira","gelado","gelatina","gelo","gema","gêmeo","general","Gilberto","ginásio","girafa","girassol","mágico","página","relógio","tigela","vegetal"};
		double revisao_nota = aleatoriar(1,10);
		Date revisao_data_envio = new Date();
		String comentario = "";
		int quantas_palavras =  aleatoriar(1,15);
		for(int i=0;i<quantas_palavras;i++){
			int qual_nome = rand.nextInt(palavras.length);
			comentario = comentario + " " + palavras[qual_nome];
		}

		Revisao revisao = new Revisao((float)revisao_nota,revisao_data_envio,comentario);
		return revisao;
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