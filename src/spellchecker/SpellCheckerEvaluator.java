package spellchecker;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

/*
 * File created on: Mar 11, 2012
 * Author: Samuel Messing <sbm2158@columbia.edu>
 */

public class SpellCheckerEvaluator {

	private static final ImmutableMap<String, String> development = createTest1Map();

	private static final ImmutableMap<String, String> developmentBigrams = createBigramTest1Map();

	private static final ImmutableMap<String, String> evaluation = createTest2Map();

	private static final ImmutableMap<String, String> evaluationBigrams = createBigramTest2Map();

	private Map<String, Integer> trainingSet;

	public static enum TestType {
		DEVELOPMENT, EVALUATION
	}

	public static enum Verboseness {
		VERBOSE, CONCISE
	}
	
	public static enum NGramType {
		UNIGRAM, BIGRAM
	}

	public SpellCheckerEvaluator(Map<String, Integer> trainingSet) {
		this.trainingSet = trainingSet;
	}

	public static Iterator<String> getTestErrors(NGramType ngram, TestType type) {
		
		Iterator<String> iterator = null;
		
		switch(ngram) {
		case UNIGRAM:
			switch(type) {
			case EVALUATION:
				iterator = evaluation.keySet().iterator();
				break;
			case DEVELOPMENT:
				iterator = development.keySet().iterator();
				break;
			}
			break;
		case BIGRAM:
			switch(type) {
			case EVALUATION:
				iterator = evaluationBigrams.keySet().iterator();
				break;
			case DEVELOPMENT:
				iterator = developmentBigrams.keySet().iterator();
				break;
			}
		}
		
		return iterator;
		
	}

	public double evaluateCorrections(Map<String, String> corrections,
			NGramType ngram, TestType type, Verboseness verbosity) {

		int count = 0;
		int correct = 0;

		if (verbosity.equals(Verboseness.VERBOSE))
			System.out.printf("Size of training corpus: %d\n", trainingSet
					.size());

		for (String error : corrections.keySet()) {
			String correctAnswer = "";
			switch(ngram) {
			case UNIGRAM:
				switch(type) {
				case DEVELOPMENT:
					correctAnswer = development.get(error);
					break;
				case EVALUATION:
					correctAnswer = evaluation.get(error);
					break;
				}
				break;
			case BIGRAM:
				switch(type) {
				case DEVELOPMENT:
					correctAnswer = developmentBigrams.get(error);
					break;
				case EVALUATION:
					correctAnswer = developmentBigrams.get(error);
					break;
				}
			}
			
			if (type.equals(TestType.DEVELOPMENT)) {
				correctAnswer = development.get(error);
			} else {
				correctAnswer = evaluation.get(error);
			}
			String proposedAnswer = corrections.get(error);
			if (correctAnswer.equals(proposedAnswer))
				correct++;
			else if (verbosity.equals(Verboseness.VERBOSE)) {
				int proposedCount = (trainingSet.get(proposedAnswer) == null) ? 0
						: trainingSet.get(proposedAnswer);
				int correctCount = (trainingSet.get(correctAnswer) == null) ? 0
						: trainingSet.get(correctAnswer);
				System.out
						.printf(
								"getCorrection('%s') - PROPOSED: %s (%d), TARGET: %s (%d)\n",
								error, proposedAnswer, proposedCount,
								correctAnswer, correctCount);
			}

			count++;
		}

		return (float) correct / count;

	}
	
	private static ImmutableMap<String, String> createBigramTest1Map() {
		Map<String, String> buildMap = new HashMap<String, String>();

		buildMap.put("where localy", "locally");
		buildMap.put("most usefull", "useful");
		buildMap.put("to concider", "consider");
		buildMap.put("a triangulaur", "triangular");
		buildMap.put("dominant hierchy", "hierarchy");
		buildMap.put("rare occurence", "occurrence");
		buildMap.put("common occurence", "occurrence");
		buildMap.put("help aranging", "arranging");
		buildMap.put("most valubale", "valuable");
		buildMap.put("extremely valuble", "valuable");
		buildMap.put("many sorces", "sources");
		buildMap.put("totally unexpcted", "unexpected");
		buildMap.put("not unexpeted", "unexpected");
		buildMap.put("almost unexspected", "unexpected");
		buildMap.put("the comittee", "committee");
		buildMap.put("in liew", "lieu");
		buildMap.put("the scisors", "scissors");
		buildMap.put("sharp sissors", "scissors");
		buildMap.put("increases transportibility", "transportability");
		buildMap.put("quite miniscule", "minuscule");
		buildMap.put("presented diagrammaticaally", "diagrammatically");
		buildMap.put("favorite poame", "poem");
		buildMap.put("upper managment", "management");
		buildMap.put("rather singulaur", "singular");
		buildMap.put("an extreamly", "extremely");
		buildMap.put("the intial", "initial");
		buildMap.put("the cemetary", "cemetery");
		buildMap.put("the semetary", "cemetery");
		buildMap.put("will supercede", "supersede");
		buildMap.put("to superceed", "supersede");
		buildMap.put("for reafreshment", "refreshment");
		buildMap.put("spiritual refreshmant", "refreshment");
		buildMap.put("for refresment", "refreshment");
		buildMap.put("spiritual refressmunt", "refreshment");
		buildMap.put("art galery", "gallery");
		buildMap.put("the gallary", "gallery");
		buildMap.put("long gallerry", "gallery");
		buildMap.put("art gallrey", "gallery");
		buildMap.put("similar pronounciation", "pronunciation");
		buildMap.put("at inconvienient", "inconvenient");
		buildMap.put("merely inconvient", "inconvenient");
		buildMap.put("at inconvinient", "inconvenient");
		buildMap.put("is totaly", "totally");
		buildMap.put("is centraly", "centrally");
		buildMap.put("i ment", "meant");
		buildMap.put("the cartains", "curtains");
		buildMap.put("the certans", "curtains");
		buildMap.put("the courtens", "curtains");
		buildMap.put("the cuaritains", "curtains");
		buildMap.put("the curtans", "curtains");
		buildMap.put("the curtians", "curtains");
		buildMap.put("the curtions", "curtains");
		buildMap.put("be somone", "someone");
		buildMap.put("most familes", "families");
		buildMap.put("stayed ther", "there");
		buildMap.put("in febuary", "february");
		buildMap.put("an extented", "extended");
		buildMap.put("the choise", "choice");
		buildMap.put("how ofen", "often");
		
		return ImmutableMap.copyOf(buildMap);
	}

	private static ImmutableMap<String, String> createBigramTest2Map() {
		Map<String, String> buildMap = new HashMap<String, String>();

		return ImmutableMap.copyOf(buildMap);
	}

	private static ImmutableMap<String, String> createTest1Map() {
		Map<String, String> buildMap = new HashMap<String, String>();

		buildMap.put("localy", "locally");
		buildMap.put("usefull", "useful");
		buildMap.put("concider", "consider");
		buildMap.put("triangulaur", "triangular");
		buildMap.put("hierchy", "hierarchy");
		buildMap.put("occurence", "occurrence");
		buildMap.put("occurence", "occurrence");
		buildMap.put("aranging", "arranging");
		buildMap.put("valubale", "valuable");
		buildMap.put("valuble", "valuable");
		buildMap.put("sorces", "sources");
		buildMap.put("unexpcted", "unexpected");
		buildMap.put("unexpeted", "unexpected");
		buildMap.put("unexspected", "unexpected");
		buildMap.put("comittee", "committee");
		buildMap.put("liew", "lieu");
		buildMap.put("scisors", "scissors");
		buildMap.put("sissors", "scissors");
		buildMap.put("transportibility", "transportability");
		buildMap.put("miniscule", "minuscule");
		buildMap.put("diagrammaticaally", "diagrammatically");
		buildMap.put("poame", "poem");
		buildMap.put("managment", "management");
		buildMap.put("singulaur", "singular");
		buildMap.put("extreamly", "extremely");
		buildMap.put("intial", "initial");
		buildMap.put("cemetary", "cemetery");
		buildMap.put("semetary", "cemetery");
		buildMap.put("supercede", "supersede");
		buildMap.put("superceed", "supersede");
		buildMap.put("reafreshment", "refreshment");
		buildMap.put("refreshmant", "refreshment");
		buildMap.put("refresment", "refreshment");
		buildMap.put("refressmunt", "refreshment");
		buildMap.put("galery", "gallery");
		buildMap.put("gallary", "gallery");
		buildMap.put("gallerry", "gallery");
		buildMap.put("gallrey", "gallery");
		buildMap.put("pronounciation", "pronunciation");
		buildMap.put("inconvienient", "inconvenient");
		buildMap.put("inconvient", "inconvenient");
		buildMap.put("inconvinient", "inconvenient");
		buildMap.put("totaly", "totally");
		buildMap.put("centraly", "centrally");
		buildMap.put("ment", "meant");
		buildMap.put("cartains", "curtains");
		buildMap.put("certans", "curtains");
		buildMap.put("courtens", "curtains");
		buildMap.put("cuaritains", "curtains");
		buildMap.put("curtans", "curtains");
		buildMap.put("curtians", "curtains");
		buildMap.put("curtions", "curtains");
		buildMap.put("somone", "someone");
		buildMap.put("familes", "families");
		buildMap.put("ther", "there");
		buildMap.put("febuary", "february");
		buildMap.put("extented", "extended");
		buildMap.put("choise", "choice");
		buildMap.put("ofen", "often");
		buildMap.put("offen", "often");
		buildMap.put("offten", "often");
		buildMap.put("ofton", "often");
		buildMap.put("undersand", "understand");
		buildMap.put("undistand", "understand");
		buildMap.put("basicaly", "basically");
		buildMap.put("descide", "decide");
		buildMap.put("particulaur", "particular");
		buildMap.put("awfall", "awful");
		buildMap.put("afful", "awful");
		buildMap.put("neccesary", "necessary");
		buildMap.put("necesary", "necessary");
		buildMap.put("neccesary", "necessary");
		buildMap.put("necassary", "necessary");
		buildMap.put("necassery", "necessary");
		buildMap.put("neccasary", "necessary");
		buildMap.put("uneque", "unique");
		buildMap.put("conciderable", "considerable");
		buildMap.put("rember", "remember");
		buildMap.put("remeber", "remember");
		buildMap.put("rememmer", "remember");
		buildMap.put("rermember", "remember");
		buildMap.put("articals", "articles");
		buildMap.put("acount", "account");
		buildMap.put("aranged", "arranged");
		buildMap.put("arrainged", "arranged");
		buildMap.put("unfortunatly", "unfortunately");
		buildMap.put("varable", "variable");
		buildMap.put("guic", "juice");
		buildMap.put("juce", "juice");
		buildMap.put("jucie", "juice");
		buildMap.put("juise", "juice");
		buildMap.put("juse", "juice");
		buildMap.put("wether", "whether");
		buildMap.put("leval", "level");
		buildMap.put("transfred", "transferred");
		buildMap.put("astablishing", "establishing");
		buildMap.put("establising", "establishing");
		buildMap.put("recieve", "receive");
		buildMap.put("benifit", "benefit");
		buildMap.put("addresable", "addressable");
		buildMap.put("remine", "remind");
		buildMap.put("remined", "remind");
		buildMap.put("cak", "cake");
		buildMap.put("fisited", "visited");
		buildMap.put("viseted", "visited");
		buildMap.put("vistid", "visited");
		buildMap.put("vistied", "visited");
		buildMap.put("problam", "problem");
		buildMap.put("proble", "problem");
		buildMap.put("promblem", "problem");
		buildMap.put("proplen", "problem");
		buildMap.put("biscits", "biscuits");
		buildMap.put("biscutes", "biscuits");
		buildMap.put("biscuts", "biscuits");
		buildMap.put("bisquits", "biscuits");
		buildMap.put("buiscits", "biscuits");
		buildMap.put("buiscuts", "biscuits");
		buildMap.put("rote", "wrote");
		buildMap.put("wote", "wrote");
		buildMap.put("compair", "compare");
		buildMap.put("pertend", "pretend");
		buildMap.put("protend", "pretend");
		buildMap.put("prtend", "pretend");
		buildMap.put("pritend", "pretend");
		buildMap.put("bicycal", "bicycle");
		buildMap.put("bycicle", "bicycle");
		buildMap.put("bycycle", "bicycle");
		buildMap.put("lagh", "laugh");
		buildMap.put("lauf", "laugh");
		buildMap.put("laught", "laugh");
		buildMap.put("lugh", "laugh");
		buildMap.put("cirtain", "certain");
		buildMap.put("receit", "receipt");
		buildMap.put("receite", "receipt");
		buildMap.put("reciet", "receipt");
		buildMap.put("recipt", "receipt");
		buildMap.put("magnificnet", "magnificent");
		buildMap.put("magificent", "magnificent");
		buildMap.put("magnifcent", "magnificent");
		buildMap.put("magnifecent", "magnificent");
		buildMap.put("magnifiscant", "magnificent");
		buildMap.put("magnifisent", "magnificent");
		buildMap.put("magnificant", "magnificent");
		buildMap.put("litriture", "literature");
		buildMap.put("hierachial", "hierarchal");
		buildMap.put("chalenges", "challenges");
		buildMap.put("chalenges", "challenges");
		buildMap.put("exstacy", "ecstasy");
		buildMap.put("ecstacy", "ecstasy");
		buildMap.put("descided", "decided");
		buildMap.put("stomac", "stomach");
		buildMap.put("stomache", "stomach");
		buildMap.put("stomec", "stomach");
		buildMap.put("stumache", "stomach");
		buildMap.put("chosing", "choosing");
		buildMap.put("futher", "further");
		buildMap.put("questionaire", "questionnaire");
		buildMap.put("speaical", "special");
		buildMap.put("specail", "special");
		buildMap.put("specal", "special");
		buildMap.put("speical", "special");
		buildMap.put("realy", "really");
		buildMap.put("relley", "really");
		buildMap.put("relly", "really");
		buildMap.put("diffrent", "different");
		buildMap.put("clearical", "clerical");
		buildMap.put("monitering", "monitoring");
		buildMap.put("biult", "built");
		buildMap.put("possition", "position");
		buildMap.put("perhapse", "perhaps");
		buildMap.put("personnell", "personnel");
		buildMap.put("seperate", "separate");
		buildMap.put("poartry", "poetry");
		buildMap.put("poertry", "poetry");
		buildMap.put("poetre", "poetry");
		buildMap.put("poety", "poetry");
		buildMap.put("powetry", "poetry");
		buildMap.put("arragment", "arrangement");
		buildMap.put("acess", "access");
		buildMap.put("vairious", "various");
		buildMap.put("beetween", "between");
		buildMap.put("stanerdizing", "standardizing");
		buildMap.put("avaible", "available");
		buildMap.put("independant", "independent");
		buildMap.put("independant", "independent");
		buildMap.put("accesing", "accessing");
		buildMap.put("lones", "loans");
		buildMap.put("discription", "description");
		buildMap.put("opisite", "opposite");
		buildMap.put("oppasite", "opposite");
		buildMap.put("oppesite", "opposite");
		buildMap.put("oppisit", "opposite");
		buildMap.put("oppisite", "opposite");
		buildMap.put("opposit", "opposite");
		buildMap.put("oppossite", "opposite");
		buildMap.put("oppossitte", "opposite");
		buildMap.put("vairiant", "variant");
		buildMap.put("poims", "poems");
		buildMap.put("pomes", "poems");
		buildMap.put("southen", "southern");
		buildMap.put("failes", "fails");
		buildMap.put("possable", "possible");
		buildMap.put("dirven", "driven");
		buildMap.put("vistors", "visitors");
		buildMap.put("completly", "completely");
		buildMap.put("levals", "levels");
		buildMap.put("experances", "experiences");
		buildMap.put("adress", "address");
		buildMap.put("adres", "address");
		buildMap.put("desicate", "desiccate");
		buildMap.put("dessicate", "desiccate");
		buildMap.put("dessiccate", "desiccate");
		buildMap.put("embaras", "embarrass");
		buildMap.put("embarass", "embarrass");
		buildMap.put("wantid", "wanted");
		buildMap.put("wonted", "wanted");
		buildMap.put("begining", "beginning");
		buildMap.put("accomodation", "accommodation");
		buildMap.put("acommodation", "accommodation");
		buildMap.put("acomodation", "accommodation");
		buildMap.put("volantry", "voluntary");
		buildMap.put("chaper", "chapter");
		buildMap.put("chaphter", "chapter");
		buildMap.put("chaptur", "chapter");
		buildMap.put("defenition", "definition");
		buildMap.put("scarcly", "scarcely");
		buildMap.put("scarecly", "scarcely");
		buildMap.put("scarely", "scarcely");
		buildMap.put("scarsely", "scarcely");
		buildMap.put("voteing", "voting");
		buildMap.put("benifits", "benefits");
		buildMap.put("auxillary", "auxiliary");
		buildMap.put("carrer", "career");
		buildMap.put("muinets", "minutes");
		buildMap.put("spledid", "splendid");
		buildMap.put("splended", "splendid");
		buildMap.put("splened", "splendid");
		buildMap.put("splended", "splendid");
		buildMap.put("contenpted", "contented");
		buildMap.put("contende", "contented");
		buildMap.put("contended", "contented");
		buildMap.put("contentid", "contented");
		buildMap.put("experance", "experience");
		buildMap.put("experiance", "experience");
		buildMap.put("perple", "purple");
		buildMap.put("perpul", "purple");
		buildMap.put("poarple", "purple");
		buildMap.put("liaision", "liaison");
		buildMap.put("liason", "liaison");
		buildMap.put("definately", "definitely");
		buildMap.put("difinately", "definitely");
		buildMap.put("annt", "aunt");
		buildMap.put("anut", "aunt");
		buildMap.put("arnt", "aunt");
		buildMap.put("planed", "planned");
		buildMap.put("defenitions", "definitions");
		buildMap.put("paralel", "parallel");
		buildMap.put("paralell", "parallel");
		buildMap.put("parrallel", "parallel");
		buildMap.put("parralell", "parallel");
		buildMap.put("parrallell", "parallel");
		buildMap.put("lates", "latest");
		buildMap.put("latets", "latest");
		buildMap.put("latiest", "latest");
		buildMap.put("latist", "latest");
		buildMap.put("inetials", "initials");
		buildMap.put("inistals", "initials");
		buildMap.put("initails", "initials");
		buildMap.put("initals", "initials");
		buildMap.put("intials", "initials");

		return ImmutableMap.copyOf(buildMap);
	}

	private static ImmutableMap<String, String> createTest2Map() {
		Map<String, String> buildMap = new HashMap<String, String>();

		buildMap.put("forbiden", "forbidden");
		buildMap.put("coments", "comments");
		buildMap.put("deciscions", "decisions");
		buildMap.put("descisions", "decisions");
		buildMap.put("supposidly", "supposedly");
		buildMap.put("embelishing", "embellishing");
		buildMap.put("tecnique", "technique");
		buildMap.put("perminantly", "permanently");
		buildMap.put("confermation", "confirmation");
		buildMap.put("appoitment", "appointment");
		buildMap.put("contuned", "continued");
		buildMap.put("progresion", "progression");
		buildMap.put("acompaning", "accompanying");
		buildMap.put("aplicable", "applicable");
		buildMap.put("regined", "regained");
		buildMap.put("guidlines", "guidelines");
		buildMap.put("serounding", "surrounding");
		buildMap.put("tittles", "titles");
		buildMap.put("unavailble", "unavailable");
		buildMap.put("advantageos", "advantageous");
		buildMap.put("brif", "brief");
		buildMap.put("apeal", "appeal");
		buildMap.put("consisiting", "consisting");
		buildMap.put("cleark", "clerk");
		buildMap.put("clerck", "clerk");
		buildMap.put("comersial", "commercial");
		buildMap.put("faverable", "favourable");
		buildMap.put("seperation", "separation");
		buildMap.put("serch", "search");
		buildMap.put("recieve", "receive");
		buildMap.put("emploies", "employees");
		buildMap.put("piror", "prior");
		buildMap.put("reulting", "resulting");
		buildMap.put("sugestion", "suggestion");
		buildMap.put("oppinion", "opinion");
		buildMap.put("cancelation", "cancellation");
		buildMap.put("compossed", "composed");
		buildMap.put("usful", "useful");
		buildMap.put("humor", "humour");
		buildMap.put("anomolies", "anomalies");
		buildMap.put("whould", "would");
		buildMap.put("doupt", "doubt");
		buildMap.put("eximination", "examination");
		buildMap.put("therefoe", "therefore");
		buildMap.put("recomend", "recommend");
		buildMap.put("seperated", "separated");
		buildMap.put("sucssuful", "successful");
		buildMap.put("succesful", "successful");
		buildMap.put("apparant", "apparent");
		buildMap.put("occureed", "occurred");
		buildMap.put("paerticulaur", "particular");
		buildMap.put("pivting", "pivoting");
		buildMap.put("anouncing", "announcing");
		buildMap.put("chalange", "challenge");
		buildMap.put("araingements", "arrangements");
		buildMap.put("proprtions", "proportions");
		buildMap.put("oranised", "organized");
		buildMap.put("acept", "accept");
		buildMap.put("dependance", "dependence");
		buildMap.put("unequaled", "unequalled");
		buildMap.put("numbuers", "numbers");
		buildMap.put("sence", "sense");
		buildMap.put("conversly", "conversely");
		buildMap.put("provid", "provide");
		buildMap.put("arrangment", "arrangement");
		buildMap.put("responsiblities", "responsibilities");
		buildMap.put("forth", "fourth");
		buildMap.put("ordenary", "ordinary");
		buildMap.put("desription", "description");
		buildMap.put("descvription", "description");
		buildMap.put("desacription", "description");
		buildMap.put("inconcievable", "inconceivable");
		buildMap.put("dsata", "data");
		buildMap.put("rgister", "register");
		buildMap.put("supervison", "supervision");
		buildMap.put("encompasing", "encompassing");
		buildMap.put("negligable", "negligible");
		buildMap.put("alow", "allow");
		buildMap.put("operatins", "operations");
		buildMap.put("executted", "executed");
		buildMap.put("interpritation", "interpretation");
		buildMap.put("heiarky", "hierarchy");
		buildMap.put("indead", "indeed");
		buildMap.put("yesars", "years");
		buildMap.put("throut", "through");
		buildMap.put("committe", "committee");
		buildMap.put("equiries", "inquiries");
		buildMap.put("befor", "before");
		buildMap.put("intresting", "interesting");
		buildMap.put("perminant", "permanent");
		buildMap.put("chose", "choose");
		buildMap.put("vertually", "virtually");
		buildMap.put("correspondance", "correspondence");
		buildMap.put("eventully", "eventually");
		buildMap.put("lonley", "lonely");
		buildMap.put("preffeson", "profession");
		buildMap.put("thay", "they");
		buildMap.put("noe", "now");
		buildMap.put("despratly", "desperately");
		buildMap.put("unversity", "university");
		buildMap.put("adjurnment", "adjournment");
		buildMap.put("possablities", "possibilities");
		buildMap.put("stoped", "stopped");
		buildMap.put("meen", "mean");
		buildMap.put("wagted", "weighted");
		buildMap.put("adequattly", "adequately");
		buildMap.put("hown", "shown");
		buildMap.put("matriiix", "matrix");
		buildMap.put("proffit", "profit");
		buildMap.put("encorage", "encourage");
		buildMap.put("colate", "collate");
		buildMap.put("disaggreagte", "disaggregate");
		buildMap.put("disaggreaget", "disaggregate");
		buildMap.put("recieving", "receiving");
		buildMap.put("reciving", "receiving");
		buildMap.put("provisoe", "proviso");
		buildMap.put("umberalla", "umbrella");
		buildMap.put("aproached", "approached");
		buildMap.put("plesent", "pleasant");
		buildMap.put("dificulty", "difficulty");
		buildMap.put("apointments", "appointments");
		buildMap.put("basse", "base");
		buildMap.put("conditining", "conditioning");
		buildMap.put("earlyest", "earliest");
		buildMap.put("begining", "beginning");
		buildMap.put("universaly", "universally");
		buildMap.put("unresloved", "unresolved");
		buildMap.put("lengh", "length");
		buildMap.put("exponentualy", "exponentially");
		buildMap.put("utalised", "utilized");
		buildMap.put("et", "set");
		buildMap.put("servays", "surveys");
		buildMap.put("sysem", "system");
		buildMap.put("aproximatly", "approximately");
		buildMap.put("ther", "their");
		buildMap.put("scheem", "scheme");
		buildMap.put("speeking", "speaking");
		buildMap.put("repetative", "repetitive");
		buildMap.put("ineffiect", "inefficient");
		buildMap.put("geniva", "geneva");
		buildMap.put("exsactly", "exactly");
		buildMap.put("imediate", "immediate");
		buildMap.put("apreciation", "appreciation");
		buildMap.put("luckeley", "luckily");
		buildMap.put("elimiated", "eliminated");
		buildMap.put("belive", "believe");
		buildMap.put("apreciated", "appreciated");
		buildMap.put("reajusted", "readjusted");
		buildMap.put("wer", "were");
		buildMap.put("where", "were");
		buildMap.put("fealing", "feeling");
		buildMap.put("anf", "and");
		buildMap.put("faulse", "false");
		buildMap.put("seeen", "seen");
		buildMap.put("interogationg", "interrogating");
		buildMap.put("academicly", "academically");
		buildMap.put("relativly", "relatively");
		buildMap.put("relitivly", "relatively");
		buildMap.put("traditionaly", "traditionally");
		buildMap.put("studing", "studying");
		buildMap.put("majorty", "majority");
		buildMap.put("biuld", "build");
		buildMap.put("agravating", "aggravating");
		buildMap.put("trasactions", "transactions");
		buildMap.put("aurguing", "arguing");
		buildMap.put("sheertes", "sheets");
		buildMap.put("sucsesive", "successive");
		buildMap.put("sucessive", "successive");
		buildMap.put("extreemly", "extremely");
		buildMap.put("especaily", "especially");
		buildMap.put("latter", "later");
		buildMap.put("sienior", "senior");
		buildMap.put("draged", "dragged");
		buildMap.put("atmospher", "atmosphere");
		buildMap.put("drasticaly", "drastically");
		buildMap.put("particulary", "particularly");
		buildMap.put("vistor", "visitor");
		buildMap.put("sesion", "session");
		buildMap.put("contually", "continually");
		buildMap.put("avaiblity", "availability");
		buildMap.put("buisy", "busy");
		buildMap.put("perametres", "parameters");
		buildMap.put("suroundings", "surroundings");
		buildMap.put("seroundings", "surroundings");
		buildMap.put("emploied", "employed");
		buildMap.put("adiquate", "adequate");
		buildMap.put("handel", "handle");
		buildMap.put("meens", "means");
		buildMap.put("familer", "familiar");
		buildMap.put("beeteen", "between");
		buildMap.put("overal", "overall");
		buildMap.put("timeing", "timing");
		buildMap.put("comittees", "committees");
		buildMap.put("commitees", "committees");
		buildMap.put("quies", "queries");
		buildMap.put("economtric", "econometric");
		buildMap.put("errounous", "erroneous");
		buildMap.put("descides", "decides");
		buildMap.put("refereence", "reference");
		buildMap.put("refference", "reference");
		buildMap.put("inteligence", "intelligence");
		buildMap.put("ediion", "edition");
		buildMap.put("ediition", "edition");
		buildMap.put("arte", "are");
		buildMap.put("appologies", "apologies");
		buildMap.put("thermawere", "thermawear");
		buildMap.put("thermawhere", "thermawear");
		buildMap.put("tecniques", "techniques");
		buildMap.put("volantary", "voluntary");
		buildMap.put("subsequant", "subsequent");
		buildMap.put("subsiquent", "subsequent");
		buildMap.put("curruntly", "currently");
		buildMap.put("forcast", "forecast");
		buildMap.put("wepons", "weapons");
		buildMap.put("rouint", "routine");
		buildMap.put("niether", "neither");
		buildMap.put("aproach", "approach");
		buildMap.put("availble", "available");
		buildMap.put("reciently", "recently");
		buildMap.put("ablity", "ability");
		buildMap.put("natior", "nature");
		buildMap.put("componant", "component");
		buildMap.put("agences", "agencies");
		buildMap.put("howeverr", "however");
		buildMap.put("sugested", "suggested");
		buildMap.put("carear", "career");
		buildMap.put("mony", "many");
		buildMap.put("anual", "annual");
		buildMap.put("acording", "according");
		buildMap.put("recives", "receives");
		buildMap.put("recieves", "receives");
		buildMap.put("expence", "expense");
		buildMap.put("relavent", "relevant");
		buildMap.put("relevaant", "relevant");
		buildMap.put("tasble", "table");
		buildMap.put("throuout", "throughout");
		buildMap.put("conferance", "conference");
		buildMap.put("sensable", "sensible");
		buildMap.put("discribed", "described");
		buildMap.put("describd", "described");
		buildMap.put("unioun", "union");
		buildMap.put("intrest", "interest");
		buildMap.put("flexable", "flexible");
		buildMap.put("reffered", "refered");
		buildMap.put("familys", "families");
		buildMap.put("suficient", "sufficient");
		buildMap.put("desention", "dissension");
		buildMap.put("adabtable", "adaptable");
		buildMap.put("representitive", "representative");
		buildMap.put("irrelavent", "irrelevant");
		buildMap.put("unessasarily", "unnecessarily");
		buildMap.put("upplied", "applied");
		buildMap.put("appologised", "apologised");
		buildMap.put("thees", "these");
		buildMap.put("thess", "these");
		buildMap.put("choises", "choices");
		buildMap.put("wil", "will");
		buildMap.put("proceduer", "procedure");
		buildMap.put("shortend", "shortened");
		buildMap.put("manualy", "manually");
		buildMap.put("dissapoiting", "disappointing");
		buildMap.put("exessively", "excessively");
		buildMap.put("containg", "containing");
		buildMap.put("develope", "develop");
		buildMap.put("creadit", "credit");
		buildMap.put("goverment", "government");
		buildMap.put("aquantences", "acquaintances");
		buildMap.put("orentated", "orientated");
		buildMap.put("widly", "widely");
		buildMap.put("advice", "advise");
		buildMap.put("dificult", "difficult");
		buildMap.put("investegated", "investigated");
		buildMap.put("bonas", "bonus");
		buildMap.put("concieved", "conceived");
		buildMap.put("nationaly", "nationally");
		buildMap.put("comppared", "compared");
		buildMap.put("compased", "compared");
		buildMap.put("moveing", "moving");
		buildMap.put("nessesity", "necessity");
		buildMap.put("oppertunity", "opportunity");
		buildMap.put("oppotunity", "opportunity");
		buildMap.put("opperttunity", "opportunity");
		buildMap.put("thorts", "thoughts");
		buildMap.put("equaled", "equalled");
		buildMap.put("scrutiniesed", "scrutinized");
		buildMap.put("analiss", "analysis");
		buildMap.put("analsis", "analysis");
		buildMap.put("analisis", "analysis");
		buildMap.put("pattarns", "patterns");
		buildMap.put("quaties", "qualities");
		buildMap.put("easyly", "easily");
		buildMap.put("oranisation", "organization");
		buildMap.put("oragnisation", "organization");
		buildMap.put("thw", "the");
		buildMap.put("hte", "the");
		buildMap.put("thi", "the");
		buildMap.put("corparate", "corporate");
		buildMap.put("citisum", "criticism");
		buildMap.put("enomosly", "enormously");
		buildMap.put("financialy", "financially");
		buildMap.put("functionaly", "functionally");
		buildMap.put("disiplin", "discipline");
		buildMap.put("anouncement", "announcement");
		buildMap.put("progressess", "progresses");
		buildMap.put("excxept", "except");
		buildMap.put("recomending", "recommending");
		buildMap.put("mathematicaly", "mathematically");
		buildMap.put("sorce", "source");
		buildMap.put("comibine", "combine");
		buildMap.put("inut", "input");
		buildMap.put("currers", "careers");
		buildMap.put("carrers", "careers");
		buildMap.put("resoved", "resolved");
		buildMap.put("diemands", "demands");
		buildMap.put("unequivocaly", "unequivocally");
		buildMap.put("suufering", "suffering");
		buildMap.put("imidatly", "immediately");
		buildMap.put("imediatly", "immediately");
		buildMap.put("acepted", "accepted");
		buildMap.put("projeccts", "projects");
		buildMap.put("necasery", "necessary");
		buildMap.put("nessasary", "necessary");
		buildMap.put("nessisary", "necessary");
		buildMap.put("neccassary", "necessary");
		buildMap.put("journaism", "journalism");
		buildMap.put("unessessay", "unnecessary");
		buildMap.put("nite", "night");
		buildMap.put("oputput", "output");
		buildMap.put("seurity", "security");
		buildMap.put("esential", "essential");
		buildMap.put("benificial", "beneficial");
		buildMap.put("benficial", "beneficial");
		buildMap.put("rquested", "requested");
		buildMap.put("suplementary", "supplementary");
		buildMap.put("questionare", "questionnaire");
		buildMap.put("empolyment", "employment");
		buildMap.put("proceding", "proceeding");
		buildMap.put("descisions", "decision");
		buildMap.put("descision", "decision");
		buildMap.put("pere", "per");
		buildMap.put("discresion", "discretion");
		buildMap.put("reching", "reaching");
		buildMap.put("analised", "analysed");
		buildMap.put("expanion", "expansion");
		buildMap.put("athough", "although");
		buildMap.put("subtrcat", "subtract");
		buildMap.put("aalysing", "analysing");
		buildMap.put("comparrison", "comparison");
		buildMap.put("monthes", "months");
		buildMap.put("hierachial", "hierarchal");
		buildMap.put("missleading", "misleading");
		buildMap.put("comit", "commit");
		buildMap.put("aurgument", "auguments");
		buildMap.put("withing", "within");
		buildMap.put("optaning", "obtaining");
		buildMap.put("acounts", "accounts");
		buildMap.put("pimarily", "primarily");
		buildMap.put("opertor", "operator");
		buildMap.put("acumulated", "accumulated");
		buildMap.put("segemnt", "segment");
		buildMap.put("thear", "there");
		buildMap.put("sumarys", "summarys");
		buildMap.put("analiss", "analyse");
		buildMap.put("understadable", "understandable");
		buildMap.put("safegaurd", "safeguard");
		buildMap.put("consisit", "consist");
		buildMap.put("declaratrions", "declarations");
		buildMap.put("muinutes", "minutes");
		buildMap.put("muiuets", "minutes");
		buildMap.put("assosiated", "associated");
		buildMap.put("accessability", "accessibility");
		buildMap.put("examin", "examine");
		buildMap.put("servaying", "surveying");
		buildMap.put("polatics", "politics");
		buildMap.put("anoying", "annoying");
		buildMap.put("agiin", "again");
		buildMap.put("accesing", "assessing");
		buildMap.put("idealy", "ideally");
		buildMap.put("variatry", "variety");
		buildMap.put("similar", "simular");
		buildMap.put("personel", "personnel");
		buildMap.put("wheras", "whereas");
		buildMap.put("whn", "when");
		buildMap.put("goegraphicaly", "geographically");
		buildMap.put("ganing", "gaining");
		buildMap.put("explaning", "explaining");
		buildMap.put("seporate", "separate");
		buildMap.put("studens", "students");
		buildMap.put("prepaired", "prepared");
		buildMap.put("generataed", "generated");
		buildMap.put("graphicaly", "graphically");
		buildMap.put("suted", "suited");
		buildMap.put("varible", "variable");
		buildMap.put("vaiable", "variable");
		buildMap.put("biulding", "building");
		buildMap.put("controled", "controlled");
		buildMap.put("reequired", "required");
		buildMap.put("nessisitates", "necessitates");
		buildMap.put("togehter", "together");
		buildMap.put("proffits", "profits");

		return ImmutableMap.copyOf(buildMap);
	}

}
