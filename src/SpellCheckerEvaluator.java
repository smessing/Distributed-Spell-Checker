import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;


/*
 * File created on: Mar 11, 2012
 * Author: Samuel Messing <sbm2158@columbia.edu>
 */

public class SpellCheckerEvaluator {
	
	private static final ImmutableMap<String, String> tests1 = createTest1Map();
	
	private static final ImmutableMap<String, String> tests2 = createTest2Map();
	
	private static ImmutableMap<String, String> createTest1Map() {
		Map<String, String> buildMap = new HashMap<String, String>();
		
		buildMap.put("localy","locally");
		buildMap.put("usefull","useful");
		buildMap.put("concider","consider");
		buildMap.put("triangulaur","triangular");
		buildMap.put("hierchy","hierarchy");
		buildMap.put("occurence","occurrence");
		buildMap.put("occurence","occurrence");
		buildMap.put("aranging","arrangeing");
		buildMap.put("valubale","valuable");
		buildMap.put("valuble","valuable");
		buildMap.put("sorces","sources");
		buildMap.put("unexpcted","unexpected");
		buildMap.put("unexpeted","unexpected");
		buildMap.put("unexspected","unexpected");
		buildMap.put("comittee","committee");
		buildMap.put("liew","lieu");
		buildMap.put("scisors","scissors");
		buildMap.put("sissors","scissors");
		buildMap.put("transportibility","transportability");
		buildMap.put("miniscule","minuscule");
		buildMap.put("diagrammaticaally","diagrammatically");
		buildMap.put("poame","poem");
		buildMap.put("managment","management");
		buildMap.put("singulaur","singular");
		buildMap.put("extreamly","extremely");
		buildMap.put("intial","initial");
		buildMap.put("cemetary","cemetery");
		buildMap.put("semetary","cemetery");
		buildMap.put("supercede","supersede");
		buildMap.put("superceed","supersede");
		buildMap.put("reafreshment","refreshment");
		buildMap.put("refreshmant","refreshment");
		buildMap.put("refresment","refreshment");
		buildMap.put("refressmunt","refreshment");
		buildMap.put("galery","gallery");
		buildMap.put("gallary","gallery");
		buildMap.put("gallerry","gallery");
		buildMap.put("gallrey","gallery");
		buildMap.put("pronounciation","pronunciation");
		buildMap.put("inconvienient","inconvenient");
		buildMap.put("inconvient","inconvenient");
		buildMap.put("inconvinient","inconvenient");
		buildMap.put("totaly","totally");
		buildMap.put("centraly","centrally");
		buildMap.put("ment","meant");
		buildMap.put("cartains","curtains");
		buildMap.put("certans","curtains");
		buildMap.put("courtens","curtains");
		buildMap.put("cuaritains","curtains");
		buildMap.put("curtans","curtains");
		buildMap.put("curtians","curtains");
		buildMap.put("curtions","curtains");
		buildMap.put("somone","someone");
		buildMap.put("familes","families");
		buildMap.put("ther","there");
		buildMap.put("febuary","february");
		buildMap.put("extented","extended");
		buildMap.put("choise","choice");
		buildMap.put("ofen","often");
		buildMap.put("offen","often");
		buildMap.put("offten","often");
		buildMap.put("ofton","often");
		buildMap.put("undersand","understand");
		buildMap.put("undistand","understand");
		buildMap.put("basicaly","basically");
		buildMap.put("descide","decide");
		buildMap.put("particulaur","particular");
		buildMap.put("awfall","awful");
		buildMap.put("afful","awful");
		buildMap.put("neccesary","necessary");
		buildMap.put("necesary","necessary");
		buildMap.put("neccesary","necessary");
		buildMap.put("necassary","necessary");
		buildMap.put("necassery","necessary");
		buildMap.put("neccasary","necessary");
		buildMap.put("uneque","unique");
		buildMap.put("conciderable","considerable");
		buildMap.put("rember","remember");
		buildMap.put("remeber","remember");
		buildMap.put("rememmer","remember");
		buildMap.put("rermember","remember");
		buildMap.put("articals","articles");
		buildMap.put("acount","account");
		buildMap.put("aranged","arranged");
		buildMap.put("arrainged","arranged");
		buildMap.put("unfortunatly","unfortunately");
		buildMap.put("varable","variable");
		buildMap.put("guic","juice");
		buildMap.put("juce","juice");
		buildMap.put("jucie","juice");
		buildMap.put("juise","juice");
		buildMap.put("juse","juice");
		buildMap.put("wether","whether");
		buildMap.put("leval","level");
		buildMap.put("transfred","transferred");
		buildMap.put("astablishing","establishing");
		buildMap.put("establising","establishing");
		buildMap.put("recieve","receive");
		buildMap.put("benifit","benefit");
		buildMap.put("addresable","addressable");
		buildMap.put("remine","remind");
		buildMap.put("remined","remind");
		buildMap.put("cak","cake");
		buildMap.put("fisited","visited");
		buildMap.put("viseted","visited");
		buildMap.put("vistid","visited");
		buildMap.put("vistied","visited");
		buildMap.put("problam","problem");
		buildMap.put("proble","problem");
		buildMap.put("promblem","problem");
		buildMap.put("proplen","problem");
		buildMap.put("biscits","biscuits");
		buildMap.put("biscutes","biscuits");
		buildMap.put("biscuts","biscuits");
		buildMap.put("bisquits","biscuits");
		buildMap.put("buiscits","biscuits");
		buildMap.put("buiscuts","biscuits");
		buildMap.put("rote","wrote");
		buildMap.put("wote","wrote");
		buildMap.put("compair","compare");
		buildMap.put("pertend","pretend");
		buildMap.put("protend","pretend");
		buildMap.put("prtend","pretend");
		buildMap.put("pritend","pretend");
		buildMap.put("bicycal","bicycle");
		buildMap.put("bycicle","bicycle");
		buildMap.put("bycycle","bicycle");
		buildMap.put("lagh","laugh");
		buildMap.put("lauf","laugh");
		buildMap.put("laught","laugh");
		buildMap.put("lugh","laugh");
		buildMap.put("cirtain","certain");
		buildMap.put("receit","receipt");
		buildMap.put("receite","receipt");
		buildMap.put("reciet","receipt");
		buildMap.put("recipt","receipt");
		buildMap.put("magnificnet","magnificent");
		buildMap.put("magificent","magnificent");
		buildMap.put("magnifcent","magnificent");
		buildMap.put("magnifecent","magnificent");
		buildMap.put("magnifiscant","magnificent");
		buildMap.put("magnifisent","magnificent");
		buildMap.put("magnificant","magnificent");
		buildMap.put("litriture","literature");
		buildMap.put("hierachial","hierarchal");
		buildMap.put("chalenges","challenges");
		buildMap.put("chalenges","challenges");
		buildMap.put("exstacy","ecstasy");
		buildMap.put("ecstacy","ecstasy");
		buildMap.put("descided","decided");
		buildMap.put("stomac","stomach");
		buildMap.put("stomache","stomach");
		buildMap.put("stomec","stomach");
		buildMap.put("stumache","stomach");
		buildMap.put("chosing","choosing");
		buildMap.put("futher","further");
		buildMap.put("questionaire","questionnaire");
		buildMap.put("speaical","special");
		buildMap.put("specail","special");
		buildMap.put("specal","special");
		buildMap.put("speical","special");
		buildMap.put("realy","really");
		buildMap.put("relley","really");
		buildMap.put("relly","really");
		buildMap.put("diffrent","different");
		buildMap.put("clearical","clerical");
		buildMap.put("monitering","monitoring");
		buildMap.put("biult","built");
		buildMap.put("possition","position");
		buildMap.put("perhapse","perhaps");
		buildMap.put("personnell","personnel");
		buildMap.put("seperate","separate");
		buildMap.put("poartry","poetry");
		buildMap.put("poertry","poetry");
		buildMap.put("poetre","poetry");
		buildMap.put("poety","poetry");
		buildMap.put("powetry","poetry");
		buildMap.put("arragment","arrangement");
		buildMap.put("acess","access");
		buildMap.put("vairious","various");
		buildMap.put("beetween","between");
		buildMap.put("stanerdizing","standardizing");
		buildMap.put("avaible","available");
		buildMap.put("independant","independent");
		buildMap.put("independant","independent");
		buildMap.put("accesing","accessing");
		buildMap.put("lones","loans");
		buildMap.put("discription","description");
		buildMap.put("opisite","opposite");
		buildMap.put("oppasite","opposite");
		buildMap.put("oppesite","opposite");
		buildMap.put("oppisit","opposite");
		buildMap.put("oppisite","opposite");
		buildMap.put("opposit","opposite");
		buildMap.put("oppossite","opposite");
		buildMap.put("oppossitte","opposite");
		buildMap.put("vairiant","variant");
		buildMap.put("poims","poems");
		buildMap.put("pomes","poems");
		buildMap.put("southen","southern");
		buildMap.put("failes","fails");
		buildMap.put("possable","possible");
		buildMap.put("dirven","driven");
		buildMap.put("vistors","visitors");
		buildMap.put("completly","completely");
		buildMap.put("levals","levels");
		buildMap.put("experances","experiences");
		buildMap.put("adress","address");
		buildMap.put("adres","address");
		buildMap.put("desicate","desiccate");
		buildMap.put("dessicate","desiccate");
		buildMap.put("dessiccate","desiccate");
		buildMap.put("embaras","embarrass");
		buildMap.put("embarass","embarrass");
		buildMap.put("wantid","wanted");
		buildMap.put("wonted","wanted");
		buildMap.put("begining","beginning");
		buildMap.put("accomodation","accommodation");
		buildMap.put("acommodation","accommodation");
		buildMap.put("acomodation","accommodation");
		buildMap.put("volantry","voluntary");
		buildMap.put("chaper","chapter");
		buildMap.put("chaphter","chapter");
		buildMap.put("chaptur","chapter");
		buildMap.put("defenition","definition");
		buildMap.put("scarcly","scarcely");
		buildMap.put("scarecly","scarcely");
		buildMap.put("scarely","scarcely");
		buildMap.put("scarsely","scarcely");
		buildMap.put("voteing","voting");
		buildMap.put("benifits","benefits");
		buildMap.put("auxillary","auxiliary");
		buildMap.put("carrer","career");
		buildMap.put("muinets","minutes");
		buildMap.put("spledid","splendid");
		buildMap.put("splended","splendid");
		buildMap.put("splened","splendid");
		buildMap.put("splended","splendid");
		buildMap.put("contenpted","contented");
		buildMap.put("contende","contented");
		buildMap.put("contended","contented");
		buildMap.put("contentid","contented");
		buildMap.put("experance","experience");
		buildMap.put("experiance","experience");
		buildMap.put("perple","purple");
		buildMap.put("perpul","purple");
		buildMap.put("poarple","purple");
		buildMap.put("liaision","liaison");
		buildMap.put("liason","liaison");
		buildMap.put("definately","definitely");
		buildMap.put("difinately","definitely");
		buildMap.put("annt","aunt");
		buildMap.put("anut","aunt");
		buildMap.put("arnt","aunt");
		buildMap.put("planed","planned");
		buildMap.put("defenitions","definitions");
		buildMap.put("paralel","parallel");
		buildMap.put("paralell","parallel");
		buildMap.put("parrallel","parallel");
		buildMap.put("parralell","parallel");
		buildMap.put("parrallell","parallel");
		buildMap.put("lates","latest");
		buildMap.put("latets","latest");
		buildMap.put("latiest","latest");
		buildMap.put("latist","latest");
		buildMap.put("inetials","initials");
		buildMap.put("inistals","initials");
		buildMap.put("initails","initials");
		buildMap.put("initals","initials");
		buildMap.put("intials","initials");
		
		return ImmutableMap.copyOf(buildMap);
	}
	
	private static ImmutableMap<String, String> createTest2Map() {
		Map<String, String> buildMap = new HashMap<String, String>();
		
		
		return ImmutableMap.copyOf(buildMap);
	}

}
