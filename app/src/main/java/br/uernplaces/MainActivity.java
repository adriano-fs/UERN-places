package br.uernplaces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Vector;

import org.osmdroid.ResourceProxy;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.MapTile;
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;


import br.exemploopenstreetmap.R;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity  extends Activity implements LocationListener  {
	public MapView osm;
	private MapController mc;
	private LocationManager locationManager;
	private String str[] ={"http://mt3.google.com/vt/lyrs=s"};
	public ArrayList<Ponto> al []= new ArrayList[3];
	public GeoPoint meuLocal ;
	Button bt_b,ml,legenda,limpar;
	EditText et;
	String []string = new String[4];
	String s;
	int nivel=1;
	boolean muda=false,limpou=false;
	
	Marker meul;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bt_b = (Button) findViewById(R.id.button1);
		ml = (Button) findViewById(R.id.button2);
		legenda = (Button) findViewById(R.id.button3);
		limpar = (Button) findViewById(R.id.button4);
		
		et = (EditText) findViewById(R.id.editText1);
		/*
		InputStream is = null;
		try {
			is = new FileInputStream("arquivo.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
		*/
      
		for(int i=1;i<al.length;i++){
			al[i]= new ArrayList<Ponto>();
		
			
			/*	
			 try {
					s = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			 string = s.split(",");
			
			al[i].add(new Ponto(Double.parseDouble(string[0]), Double.parseDouble(string[1]), string[2], string[3], 0));
			*/
			
			
			
			//ROMARIO E JAEDSON
			al[i].add(new Ponto(-5.20678, -37.3156,"DCE","Diretório Central dos Estudantes",R.drawable.institucional));
			al[i].add(new Ponto(-5.20686, -37.31552,"RU","Restaurante Universitário",R.drawable.restaurante));
			al[i].add(new Ponto(-5.20683, -37.31551,"CC","Centro de Convivência",R.drawable.controconvivencia));
			al[i].add(new Ponto(-5.20694, -37.31539,"ARFOR","Secretaria Geral",R.drawable.comum));
			al[i].add(new Ponto(-5.20691, -37.31515,"Diretoria de Relações Internacionais e Interinstitucionais","",R.drawable.comum));
			al[i].add(new Ponto(-5.20691, -37.31513,"Comitê de Ética / Comitê de Experimentação Animal","",R.drawable.comum));
			al[i].add(new Ponto(-5.20692, -37.31508,"Xerox","",R.drawable.xerox));
			al[i].add(new Ponto(-5.20743, -37.31459,"Caixa Eletrônico","",R.drawable.caixaeletronic));
			al[i].add(new Ponto(-5.20745, -37.31462,"DAE","Diretoria de Assistência Estudantil",R.drawable.comum));
			al[i].add(new Ponto(-5.20743, -37.31453,"Biblioteca de Periódicos","",R.drawable.biblioteca48));
			al[i].add(new Ponto(-5.20741, -37.31451,"Sala de estudo Coletivo","",R.drawable.comum));
			al[i].add(new Ponto(-5.20739, -37.31436,"Biblioteca","",R.drawable.biblioteca48));
			al[i].add(new Ponto(-5.20736, -37.31462,"Sala de Coordenação e Secretaria (Biblioteca)","",R.drawable.comum));
			al[i].add(new Ponto(-5.20733, -37.31459,"Sala de Seleção de Aquisição (Biblioteca)","",R.drawable.comum));
			al[i].add(new Ponto(-5.20732, -37.31462,"Sala de Restauração","",R.drawable.comum));
			al[i].add(new Ponto(-5.20697, -37.31452,"DIRCA","Diretoria de Registro e Controle Acadêmico",R.drawable.comum));
			al[i].add(new Ponto(-5.20695, -37.31456,"DAIN","Diretoria de Apoio a Inclusão",R.drawable.departamento));
			al[i].add(new Ponto(-5.20688, -37.31451,"Secretaria da PROEG","",R.drawable.comum));
			al[i].add(new Ponto(-5.20685, -37.31454,"Administração de Cursos de Graduação","",R.drawable.institucional));
			al[i].add(new Ponto(-5.20677, -37.31467,"PROEG","Pro-Reitoria de Ensino de Graduação",R.drawable.institucional));
			al[i].add(new Ponto(-5.20674, -37.3145,"Docência Universitária - Administração de Cursos de Graduação - PROEG","",R.drawable.ic_launcher));
			al[i].add(new Ponto(-5.20655, -37.31453,"Sub-prefeitura do Campus Central","",R.drawable.comum));
			al[i].add(new Ponto(-5.20652, -37.31452,"Diretoria de Pesquisa","",R.drawable.comum));
			al[i].add(new Ponto(-5.20654, -37.31458,"CONSEPE/CONSUNI","Câmaras de Ensino de Graduação",R.drawable.comum));
			al[i].add(new Ponto(-5.20651, -37.31459,"Diretoria de Pos-Graduação","",R.drawable.comum));
			al[i].add(new Ponto(-5.20654, -37.31469,"PROEX","Pró-Reitoria de Extensão",R.drawable.comum));
			al[i].add(new Ponto(-5.20651, -37.31473,"DECA","Diretoria de Educação, Cultura e Artes",R.drawable.comum));
			al[i].add(new Ponto(-5.2069, -37.31345,"Departamento de Música","",R.drawable.departamento));
			al[i].add(new Ponto(-5.20689, -37.31356,"Laboratório de Informática - Música","",R.drawable.labinfo));
			al[i].add(new Ponto(-5.20688, -37.31362,"Conservatório de Música","",R.drawable.comum));
			al[i].add(new Ponto(-5.20689, -37.31366,"GPPEM","Grupo de Pesquisa em Música",R.drawable.comum));
			al[i].add(new Ponto(-5.20687, -37.31373,"PROFLETRAS","Mestrado de Letras",R.drawable.comum));
			al[i].add(new Ponto(-5.20671, -37.31358,"Salas de Aula - Música","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.20653, -37.31343,"LT","Laboratório de Teclado",R.drawable.labciencia));
			al[i].add(new Ponto(-5.20652, -37.3135,"LPI","Laboratório de Prática Instrumental",R.drawable.labciencia));
			al[i].add(new Ponto(-5.20632, -37.31369,"Salas de Aula - Ciências Econômicas","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.2061, -37.31365,"LABECO","Laboratório Integrado de Análise Ambiental e Ecológico Aplicado",R.drawable.labciencia));
			al[i].add(new Ponto(-5.20607, -37.31378,"FACEM","Faculdade de Ciências Econômicas",R.drawable.comum));
			al[i].add(new Ponto(-5.20609, -37.31392,"Salas de Aula Administração/Gestão Ambiental","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.20591, -37.3136,"Laboratório de Hotelaria","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.2059, -37.31367,"Sala de Projeto - Turismo","",R.drawable.comum));
			al[i].add(new Ponto(-5.20609, -37.31409,"NESAT","Núcleo de Estudos Socioambientais e Territoriais",R.drawable.comum));
			al[i].add(new Ponto(-5.20609, -37.31416,"GPGFC","Grupo de Pesquisa em Gestão, Finanças e Contabilidade",R.drawable.comum));
			al[i].add(new Ponto(-5.20609, -37.31422,"DGA","Departamento de Gestão Ambiental",R.drawable.departamento));
			al[i].add(new Ponto(-5.20609, -37.31426,"DETUR","Departamento de Turismo",R.drawable.departamento));
			al[i].add(new Ponto(-5.20608, -37.31432,"LABEA","Laboratório de estudos e Pesquisas em Educação Ambiental",R.drawable.labciencia));
			al[i].add(new Ponto(-5.20608, -37.3144,"NUDET","Núcleo de Desenvolvimento Territorial",R.drawable.comum));
			al[i].add(new Ponto(-5.20606, -37.31452,"Laboratório de Informática - FACEM","",R.drawable.labinfo));
			al[i].add(new Ponto(-5.20607, -37.31456,"DAD","Departamento de Administração",R.drawable.departamento));
			al[i].add(new Ponto(-5.20588, -37.31412,"Salas de Aulas - Jornalismo","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.20587, -37.3142,"Laboratório de Informática - Comunicação Social","",R.drawable.labinfo));
			al[i].add(new Ponto(-5.20587, -37.31428,"Rádio Univesitária","",R.drawable.comum));
			al[i].add(new Ponto(-5.20586, -37.31436,"DECOM","Departamento de Comunicação Social",R.drawable.departamento));
			al[i].add(new Ponto(-5.2059, -37.31449,"Secretaria de Ciências Econômicas","",R.drawable.comum));
			al[i].add(new Ponto(-5.20586, -37.31459,"Salas de Aulas - Ciências Contábeis","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.20568, -37.31403,"Sala de Multimídia da FAFIC","",R.drawable.comum));
			al[i].add(new Ponto(-5.20567, -37.31409,"Laboratório de Áudio-Visual da FAFIC","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.20566, -37.31415,"Salas de Aulas - Radialismo","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.20567, -37.31421,"Salas de Aulas - Publicidade e Propaganda","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.20564, -37.31453,"Salas de Aulas - Ciências Sociais","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.20562, -37.31473,"DEaD","Diretoria de Educação a Distância",R.drawable.comum));
			al[i].add(new Ponto(-5.20561, -37.3148,"PPGCISH","Programa de Pos-Graduação em Ciências Sociais e Humanas",R.drawable.comum));
			al[i].add(new Ponto(-5.20526, -37.3145,"MNEMIS","Grupo de Pesquisa Memória, Identidade e Ensino de Hístoria",R.drawable.comum));
			al[i].add(new Ponto(-5.20525, -37.31455,"Secretaria do PPGCISH","",R.drawable.comum));
			al[i].add(new Ponto(-5.20524, -37.31459,"Sala de Reuniões do PPGCISH","",R.drawable.comum));
			al[i].add(new Ponto(-5.20524, -37.31463,"GCOM","Grupo de Pesquisa em Comunicação, Cultura e Sociedade",R.drawable.comum));
			al[i].add(new Ponto(-5.20523, -37.31467,"Grupo de Pesquisa Estado Segurança Pública e Cidadania","",R.drawable.comum));
			al[i].add(new Ponto(-5.20523, -37.31473,"Grupo de Pesquisa História do Nordeste: Sociedade e Cultura","",R.drawable.comum));
			al[i].add(new Ponto(-5.20523, -37.31477,"Sala PIBID - Geografia","",R.drawable.comum));
			al[i].add(new Ponto(-5.20522, -37.31481,"GECOM","Grupo de Pesquisa do Pensamento Complexo",R.drawable.comum));
				
			
			//GETULIO
			al[i].add(new Ponto(-5.205321, -37.317157, "PPGSS","Programa de Pós-graduação em Saúde e Sociedade",R.drawable.comum));
			al[i].add(new Ponto(-5.205317, -37.317217, "GPELL","Grupo de Pesquisa Liguistica e Literatura",R.drawable.comum));
			al[i].add(new Ponto(-5.205312, -37.317308, "GET", "Grupo de Estudo em Tradução",R.drawable.comum));
			al[i].add(new Ponto(-5.205308, -37.317328, "Secretaria","",R.drawable.comum));
			al[i].add(new Ponto(-5.205311, -37.317359,"GEDUERN", "Grupo de Estudos de Discurso da UERN",R.drawable.comum));
			al[i].add(new Ponto(-5.205142, -37.317029,"Secretaria - FALA", "",R.drawable.comum));
			al[i].add(new Ponto(-5.205129, -37.317085, "Coordenação e Secretaria de Língua Portuguesa à Distância", "",R.drawable.comum));
			al[i].add(new Ponto(-5.205131, -37.317102, "Departamento de Letras Estrangeiras","",R.drawable.departamento));
			al[i].add(new Ponto(-5.205128, -37.317125,"Sala de aula","",R.drawable.comum));
			al[i].add(new Ponto(-5.205120, -37.317173, "Laboratório","",R.drawable.labinfo));
			al[i].add(new Ponto(-5.205112, -37.317211, "Departamento de Letras Vernáculas","",R.drawable.departamento));

			al[i].add(new Ponto(-5.204707, -37.317217,"Sala de Dança","",R.drawable.comum));
			al[i].add(new Ponto(-5.204301, -37.317086,"Piscina","",R.drawable.piscina));
			
			//GETULIO
			al[i].add(new Ponto(-5.205370, -37.316507, "LES", "Laboratório de Engenharia de Software",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205367, -37.316581, "PET CC","",R.drawable.comum));
			al[i].add(new Ponto(-5.205363, -37.316608, "Pós-graduação em Física","",R.drawable.comum));
			al[i].add(new Ponto(-5.205359, -37.316649, "GEPEES", "Grupo de estudo e pesquisa em estado, educação e sociedade",R.drawable.comum));
			al[i].add(new Ponto(-5.205355, -37.316676, "Formação e Profissionalização de Professores","",R.drawable.comum));
			al[i].add(new Ponto(-5.205347, -37.316722, "Grupo de estudo e pesquisa  psicológica e educação inclusiva","",R.drawable.comum));
			al[i].add(new Ponto(-5.205171, -37.316473, "Mestrado em Serviço Social","",R.drawable.comum));
			al[i].add(new Ponto(-5.205166, -37.316548, "Secretaria FASSO e Serviço Social","",R.drawable.comum));
			al[i].add(new Ponto(-5.205160, -37.316578, "Departamento de Serviço Social","",R.drawable.departamento));
			al[i].add(new Ponto(-5.205153, -37.316605, "NEPT","",R.drawable.comum));
			al[i].add(new Ponto(-5.205149, -37.316636, "NEM","",R.drawable.comum));
			al[i].add(new Ponto(-5.205151, -37.316660, "Coordenação de Estagiários","",R.drawable.comum));
			al[i].add(new Ponto(-5.205148, -37.316680, "Biblioteca Setorial","",R.drawable.biblioteca48));
			al[i].add(new Ponto(-5.205143, -37.316704, "Sala de Estudos","",R.drawable.comum));
			al[i].add(new Ponto(-5.205141, -37.316726, "Centro Acadêmico","",R.drawable.comum));
			al[i].add(new Ponto(-5.205142, -37.316764, "Núcleo de Estudo e Ações na Área da Criança e do Adolescente","",R.drawable.comum));
			al[i].add(new Ponto(-5.204953, -37.316458, "Auditório FASSO","",R.drawable.auditorio48));
			al[i].add(new Ponto(-5.204936, -37.316606, "Sala de aula","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.204924, -37.316747, "Sala de aula","",R.drawable.salaaula));
			
			al[i].add(new Ponto(-5.204266, -37.316487, "Ginásio","",R.drawable.comum));
			al[i].add(new Ponto(-5.204196, -37.316337, "LES 2", "Laboratório de Engenharia de Software",R.drawable.labciencia));
			
			//CAIO
			al[i].add(new Ponto(-5.204796, -37.316021,"LEM","Laboratório de Ensino de Matemática",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204800, -37.315961,"LEC","Laboratório de Ensino de Computação",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204810, -37.315910,"Sala de Video Conferência","",R.drawable.comum));
			al[i].add(new Ponto(-5.204816, -37.315794,"Salas de Aula - Ciência da Computação / Matemática","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.204868, -37.315615,"LORDI","Laboratório de Redes e Sistemas Distribuídos",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204862, -37.315674,"LOIA","Laboratório de Otimização e Inteligência Artificial",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204881, -37.315520,"Sala de Multimídia da FANAT","",R.drawable.comum));
			al[i].add(new Ponto(-5.204919, -37.315521,"Laboratório de Historia Oral e Imagem / Núcleo de Documentação e Pesquisa Histórica","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204908, -37.315663,"LABINFO","Laboratório de Informática da FANAT",R.drawable.labinfo));
			al[i].add(new Ponto(-5.204851, -37.315393,"Salas de Aula - Física","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.204873, -37.315177,"Salas de Aula - Química","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.205232, -37.315734,"Laboratório de Química I","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205229, -37.315799,"Laboratório de Mecânica I e II","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205222, -37.315863,"Laboratório de Biologia I","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205216, -37.315923,"Laboratório de Biologia II","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205211, -37.315979,"Laboratório de Física Moderna / Ópticas e Ondas","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205206, -37.316043,"Laboratório de Química II","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205261, -37.315505,"Direção da FAFIC","",R.drawable.comum));
			al[i].add(new Ponto(-5.205267, -37.315452,"Secretaria e Departamento - Ciências Socias e Políticas","",R.drawable.comum));
			al[i].add(new Ponto(-5.205272, -37.315397,"Secretaria e Departamento - História","",R.drawable.comum));
			al[i].add(new Ponto(-5.205278, -37.315289,"Auditório da FAFIC","",R.drawable.auditorio48));
			al[i].add(new Ponto(-5.205288, -37.315191,"Auditório Reitor Helder Heronildes da Silva","",R.drawable.auditorio48));
			al[i].add(new Ponto(-5.205021, -37.315726,"Secretaria e Direção da FANAT","",R.drawable.comum));
			al[i].add(new Ponto(-5.205020, -37.315760,"Sala do Professor Rommel Wladimir","",R.drawable.comum));
			al[i].add(new Ponto(-5.205021, -37.315790,"DI","Departamento de Informática",R.drawable.departamento));
			al[i].add(new Ponto(-5.205020, -37.315821,"Departamento de Matemática","",R.drawable.departamento));
			al[i].add(new Ponto(-5.205020, -37.315854,"Departamento de Ciências Biologicas","",R.drawable.departamento));
			al[i].add(new Ponto(-5.204638, -37.315700,"Laboratório de Cultura de Tecido Vegetal","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204629, -37.315754,"Laboratório de Cromatografia","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204612, -37.315959,"Laboratório de Ciências Biologicas","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205683, -37.315938,"Salas de Aula - Pedagogia","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.205590, -37.315588,"Laboratório de Geoprocessamento","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205638, -37.315597,"Laboratório de Arqueologia Homem Potiguar","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205625, -37.315729,"Centro Acadêmico - Pedagogia","",R.drawable.comum));
			al[i].add(new Ponto(-5.206066, -37.316803,"Ônibus","Parada de Onibus",R.drawable.onibus));
			//CAIO
		
			
			al[i].add(new Ponto(-5.205511, -37.316034,"Salas de aula - Pedagogia","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.205513, -37.315925,"Biblioteca setorial da FE","",R.drawable.biblioteca48));
			al[i].add(new Ponto(-5.205522, -37.315855,"Sala Multimidia II - Pedagogia","",R.drawable.comum));
			al[i].add(new Ponto(-5.205527, -37.315788,"Secretaria da faculdade de educação e pós-graduação","",R.drawable.comum));
			al[i].add(new Ponto(-5.205580, -37.315719,"PET de pedagogia","",R.drawable.comum));
			al[i].add(new Ponto(-5.205962, -37.315516,"Salas de aula - Direito","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.205976, -37.315410,"Secretaria de Direito","",R.drawable.comum));
			al[i].add(new Ponto(-5.205985, -37.315376,"Departamento de Direito","",R.drawable.departamento));
			al[i].add(new Ponto(-5.205982, -37.315290,"Salas de aula - Direito","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.205759, -37.315485,"Salas de aula - Direito","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.205795, -37.315242,"Departamento de Ciências Contábeis","",R.drawable.departamento));
			al[i].add(new Ponto(-5.205787, -37.315295,"Departamento de Ciências Econômicas","",R.drawable.departamento));
			al[i].add(new Ponto(-5.205778, -37.315356,"Centro Acadêmico - Ciências Econômimcas","",R.drawable.comum));
			al[i].add(new Ponto(-5.205546, -37.315470,"Sala de Cartografia","",R.drawable.comum));
			al[i].add(new Ponto(-5.205558, -37.315430,"Laboratório de Geografia física","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205558, -37.315385,"Laboratório de Greografia humana","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.205563, -37.315345,"Secretaria e departamento de Geografia","",R.drawable.departamento));
			al[i].add(new Ponto(-5.205569, -37.315295,"Secretaria e departamento de Filosofia","",R.drawable.departamento));
			al[i].add(new Ponto(-5.205577, -37.315224,"PET de Ciências sociais e políticas","",R.drawable.comum));
			al[i].add(new Ponto(-5.205942, -37.315819,"Secretaria e direção da FE","",R.drawable.comum));
			al[i].add(new Ponto(-5.205934, -37.315870,"Orientação Acadêmica - FE","",R.drawable.comum));
			al[i].add(new Ponto(-5.205928, -37.315923,"Departamento de Educação","",R.drawable.departamento));
			al[i].add(new Ponto(-5.205925, -37.315971,"Coordenação de Educação","",R.drawable.comum));
			al[i].add(new Ponto(-5.205923, -37.316013,"PIBID - UERN","",R.drawable.comum));
			al[i].add(new Ponto(-5.205917, -37.316081,"Sala multimidia I - Pedagogia","",R.drawable.comum));
			al[i].add(new Ponto(-5.205038, -37.315409,"Salas de aula - Biologia e Geografia","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.205067, -37.315197,"Salas de aula - Química e História","",R.drawable.salaaula));
			al[i].add(new Ponto(-5.205005, -37.315916,"Departamento de Química","",R.drawable.departamento));
			al[i].add(new Ponto(-5.205002, -37.315966,"Departamento de Física","",R.drawable.departamento));
			al[i].add(new Ponto(-5.204491, -37.316179,"Auditório da PRODEP","",R.drawable.auditorio48));
			al[i].add(new Ponto(-5.204488, -37.316101,"Coordenação e secretaria da PRODEP","",R.drawable.comum));
			al[i].add(new Ponto(-5.204404, -37.316188,"Laboratório de microbiologia","",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204356, -37.316146,"LABSECO","Laboratório de sistemática e ecologia animal",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204280, -37.316186,"LABFU","Laboratório de biologia funcional",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204238, -37.316138,"LESV","Laboratório de ecologia e sistemática vegetal",R.drawable.labciencia));
			al[i].add(new Ponto(-5.204191, -37.316161,"LAMOp","Laboratório de analises magneticas e opticas",R.drawable.labciencia));
			
		}
			
		al[0]= new ArrayList<Ponto>();
		al[0].add(new Ponto(-5.205720, -37.315977,"FE","Faculdade de Educação",R.drawable.comum));
		al[0].add(new Ponto(-5.205746, -37.315395,"FAD","Faculdade de Direito",R.drawable.comum));
		al[0].add(new Ponto(-5.205050, -37.317230,"FALA","Faculdade de Letras e Artes",R.drawable.comum));
		al[0].add(new Ponto(-5.205123, -37.316566,"FASSO","Faculdade de Serviço Social",R.drawable.comum));
		al[0].add(new Ponto(-5.205004, -37.315891,"FANAT","Faculdade de Ciencias Exatas e Naturais",R.drawable.comum));
		al[0].add(new Ponto(-5.204729, -37.316550,"FAEF","Faculdade de Educacao Fisica",R.drawable.comum));
		al[0].add(new Ponto(-5.20607, -37.31378,"FACEM","Faculdade de Ciências Econômicas",R.drawable.comum));
		al[0].add(new Ponto(-5.205015, -37.315310,"FAFIC","Faculdade de Filosofia e Ciências Sociais",R.drawable.comum));
		al[0].add(new Ponto(-5.206848, -37.315509,"CC","Centro de Convivência",R.drawable.comum));
		al[0].add(new Ponto(-5.206583, -37.314689,"Pró-reitorias","",R.drawable.comum));
		al[0].add(new Ponto(-5.207435, -37.314459,"Biblioteca","",R.drawable.comum));
		
		
		
	
		File dir = new File("/sdcard/osmdroid/tiles/Google Maps/");
		if(!dir.exists())		
			copyFilesToSdCard();
		
		
		
		bt_b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String palavra = et.getText().toString().toLowerCase();
				Ponto p;
				osm.getOverlays().clear();
				int i,j;
				Toast toast = null ;
				boolean achou=false;
				for(i=0;i<al.length;i++){
					
					for(j=0;j<al[i].size();j++){
						p = al[i].get(j);
						
						if(p.getTitle().toLowerCase().indexOf(palavra)!=-1 ||
								p.getDesc().toLowerCase().indexOf(palavra)!=-1){
							achou=true;
							addMarker(new GeoPoint(p.getX(),p.getY()), p.getTitle(), p.getDesc(),p.getI());
						}	
					}			
				}
				
				if(!achou){
					toast = Toast.makeText(getApplicationContext(), "Pesquisa sem retorno", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});
	
		
		
		limpar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(limpou==false){
					limpou=true;
					osm.getOverlays().clear();
					mc.animateTo(osm.getMapCenter());
				}
					
				else if(limpou==true){
					
					adicionar();
				}
					
				
		
			}
		});
		
		ml.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Toast toast;
						
				osm.getOverlays().remove(meul);
				if(meuLocal!=null){
					meul = addMarker(meuLocal,"Meu local","",R.drawable.usuario);
					mc.animateTo(meuLocal);
				}
					
				else{
					toast = Toast.makeText(getApplicationContext(), "Sem localizacao!!", Toast.LENGTH_SHORT);
					toast.show();
				}
				
		
			}
		});
	
		
		legenda.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent (getApplicationContext(),Legenda.class); 
				
				startActivity(i);
			
			}
		});
		
		
		osm = (MapView) findViewById(R.id.mapView);
		//osm.setTileSource(TileSourceFactory.MAPNIK);
		
		
		osm.setTileSource(new OnlineTileSourceBase("Google Maps",ResourceProxy.string.unknown,14,20,256,".png",str) {
			
			@Override
			public String getTileURLString(MapTile arg0) {
				
				return getBaseUrl() + "&x=" + arg0.getX() + "&y=" + arg0.getY() + "&z=" + arg0.getZoomLevel();
			
			}
		});
				
				
				
		osm.setUseDataConnection(false);
	
		osm.setBuiltInZoomControls(true);
		osm.setMultiTouchControls(true);
		
		
		mc = (MapController) osm.getController();
		mc.setZoom(16);
		
		GeoPoint center = new GeoPoint(-5.2046106,-37.3162137);
		mc.animateTo(center);
		Ponto p;
		
		adicionar();
		
		osm.setMapListener(new MapListener() {
			
			@Override
			public boolean onZoom(ZoomEvent arg0){
				int z = osm.getZoomLevel();
				
				//if(et.getText().toString()==null){
					if(nivel==1&&z>17){
						nivel=2;
						adicionar();
					}
					else if(nivel==2&&z<=17){
						nivel=1;
						adicionar();
					}
				//}
				
					
				return false;
			
			
			}
			
			@Override
			public boolean onScroll(ScrollEvent arg0) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		
	}	
		
	
	public void adicionar(){
		
		limpou=false;
		int z = osm.getZoomLevel();
		Ponto p;
		if(z>17){
			osm.getOverlays().clear();
			muda=true;
			for(int i=0; i<al[1].size(); i++){
				p = al[1].get(i);	
				addMarker(new GeoPoint(p.getX(),p.getY()), p.getTitle(), p.getDesc(),p.getI());
			}		
		}
		else if(z<=17){
			osm.getOverlays().clear();
			muda=false;
			
			for(int i=0; i<al[0].size(); i++){
				p = al[0].get(i);	
				addMarker(new GeoPoint(p.getX(),p.getY()), p.getTitle(), p.getDesc(),p.getI());
			}
		}
	}
	
	
	public Marker addMarker(GeoPoint center,String t,String d,int i){
		Marker marker = new Marker(osm);
		marker.setPosition(center);
		marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
		if(i!=0)
			marker.setIcon(getResources().getDrawable(i));
		
		marker.setTitle(t);
		marker.setSnippet(d);
		//marker.setSubDescription(d);
		
		
		osm.getOverlays().add(marker);
		
		osm.invalidate();
		return marker;
	}
	
	
	

	
	final static String TARGET_BASE_PATH = "/sdcard/osmdroid/tiles/Google Maps/";

    private void copyFilesToSdCard() {
        copyFileOrDir(""); // copy all files in assets folder in my project
    }

    private void copyFileOrDir(String path) {
        AssetManager assetManager = this.getAssets();
        String assets[] = null;
        try {
            Log.i("tag", "copyFileOrDir() "+path);
            
            assets = assetManager.list(path);
            if (assets.length == 0) {
                copyFile(path);
            } else {
                String fullPath =  TARGET_BASE_PATH + path;
                Log.i("tag", "path="+fullPath);
                File dir = new File(fullPath);
                if (!dir.exists() && !path.startsWith("images") && !path.startsWith("sounds") && !path.startsWith("webkit"))
                    if (!dir.mkdirs())
                        Log.i("tag", "could not create dir "+fullPath);
                for (int i = 0; i < assets.length; ++i) {
                    String p;
                    if (path.equals(""))
                        p = "";
                    else 
                        p = path + "/";

                    if (!path.startsWith("images") && !path.startsWith("sounds") && !path.startsWith("webkit"))
                        copyFileOrDir( p + assets[i]);
                }
            }
        } catch (IOException ex) {
            Log.e("tag", "I/O Exception", ex);
        }
    }

    private void copyFile(String filename) {
        AssetManager assetManager = this.getAssets();

        InputStream in = null;
        OutputStream out = null;
        String newFileName = null;
        try {
            Log.i("tag", "copyFile() "+filename);
            in = assetManager.open(filename);
            if (filename.endsWith(".jpg")) // extension was added to avoid compression on APK file
                newFileName = TARGET_BASE_PATH + filename.substring(0, filename.length()-4);
            else
                newFileName = TARGET_BASE_PATH + filename;
            out = new FileOutputStream(newFileName);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            Log.e("tag", "Exception in copyFile() of "+newFileName);
            Log.e("tag", "Exception in copyFile() "+e.toString());
        }

    }

	@Override
	public void onLocationChanged(Location location) {
		meuLocal = new GeoPoint(location.getLatitude(), location.getLongitude());
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
