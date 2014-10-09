package edu.stanford.nlp.mt.decoder.feat;

import java.util.List;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import edu.stanford.nlp.mt.util.FeatureValue;
import edu.stanford.nlp.mt.util.Featurizable;
import edu.stanford.nlp.mt.util.IString;
import edu.stanford.nlp.mt.decoder.feat.RuleFeaturizer;
import edu.stanford.nlp.util.Generics;
import edu.stanford.nlp.mt.util.Sequence;


/**
 * A rule featurizer.
 */
public class EndsWithFunctionWord implements RuleFeaturizer<IString, String> {

  private static final String FEAT_NAME = "FUNCTION_WORD";

  private static final Set<String> functionWords = new HashSet<String>(Arrays.asList(
    "a", "about", "above", "after", "again", "ago", "all", "almost", "along", "already", "also", "although", "always", "am", "among", "an", "and", "another", "any", "anybody", "anything", "anywhere", "are", "aren't", "around", "as", "at", "back", "else", "be", "been", "before", "being", "below", "beneath", "beside", "between", "beyond", "billion", "billionth", "both", "each", "but", "by", "can", "can't", "could", "couldn't", "did", "didn't", "do", "does", "doesn't", "doing", "done", "don't", "down", "during", "eight", "eighteen", "eighteenth", "eighth", "eightieth", "eighty", "either", "eleven", "eleventh", "enough", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "except", "far", "few", "fewer", "fifteen", "fifteenth", "fifth", "fiftieth", "fifty", "first", "five", "for", "fortieth", "forty", "four", "fourteen", "fourteenth", "fourth", "hundred", "from", "get", "gets", "getting", "got", "had", "hadn't", "has", "hasn't", "have", "haven't", "having", "he", "he'd", "he'll", "hence", "her", "here", "hers", "herself", "he's", "him", "himself", "his", "hither", "how", "however", "near", "hundredth", "i", "i'd", "if", "i'll", "i'm", "in", "into", "is", "i've", "isn't", "it", "its", "it's", "itself", "just", "last", "less", "many", "me", "may", "might", "million", "millionth", "mine", "more", "most", "much", "must", "mustn't", "my", "myself", "near", "nearby", "nearly", "neither", "never", "next", "nine", "nineteen", "nineteenth", "ninetieth", "ninety", "ninth", "no", "nobody", "none", "noone", "nothing", "nor", "not", "now", "nowhere", "of", "off", "often", "on", "or", "once", "one", "only", "other", "others", "ought", "oughtn't", "our", "ours", "ourselves", "out", "over", "quite", "rather", "round", "second", "seven", "seventeen", "seventeenth", "seventh", "seventieth", "seventy", "shall", "shan't", "she'd", "she", "she'll", "she's", "should", "shouldn't", "since", "six", "sixteen", "sixteenth", "sixth", "sixtieth", "sixty", "so", "some", "somebody", "someone", "something", "sometimes", "somewhere", "soon", "still", "such", "ten", "tenth", "than", "that", "that", "that's", "the", "their", "theirs", "them", "themselves", "these", "then", "thence", "there", "therefore", "they", "they'd", "they'll", "they're", "third", "thirteen", "thirteenth", "thirtieth", "thirty", "this", "thither", "those", "though", "thousand", "thousandth", "three", "thrice", "through", "thus", "till", "to", "towards", "today", "tomorrow", "too", "twelfth", "twelve", "twentieth", "twenty", "twice", "two", "under", "underneath", "unless", "until", "up", "us", "very", "when", "was", "wasn't", "we", "we'd", "we'll", "were", "we're", "weren't", "we've", "what", "whence", "where", "whereas", "which", "while", "whither", "who", "whom", "whose", "why", "will", "with", "within", "without", "won't", "would", "wouldn't", "yes", "yesterday", "yet", "you", "your", "you'd", "you'll", "you're", "yours", "yourself", "yourselves", "you've"
  ));

  @Override
  public void initialize() {
    // Do any setup here.
  }

  @Override
  public List<FeatureValue<String>> ruleFeaturize(
      Featurizable<IString, String> f) {
    List<FeatureValue<String>> features = Generics.newLinkedList();

    if (f.targetPhrase != null && f.targetPhrase.size() > 0) {
      String lastWord = f.targetPhrase.get(f.targetPhrase.size() - 1).toString();
      if (functionWords.contains(lastWord)) {
        features.add(new FeatureValue<String>(FEAT_NAME, 1.0));
      }
    }

    return features;
  }

  @Override
  public boolean isolationScoreOnly() {
    return false;
  }
}
