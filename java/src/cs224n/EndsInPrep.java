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
public class EndsInPrep implements RuleFeaturizer<IString, String> {
  
  private static final Set<String> preps = new HashSet<String>(Arrays.asList("about", "above", "across", "after", "against", "along", "among", "around", "as", "at", "before", "behind", "below", "beneath", "beside", "between", "beyond", "by", "concerning", "despite", "down", "during", "except", "excepting", "for", "from", "in", "inside", "into", "like", "near", "next", "of", "off", "on", "onto", "out", "outside", "over", "past", "regarding", "round", "since", "through", "throughout", "till", "to", "toward", "under", "underneath", "unlike", "until", "up", "upon", "with", "within", "without"));

  @Override
  public void initialize() {
    // Do any setup here.
  }

  @Override
  public List<FeatureValue<String>> ruleFeaturize(
      Featurizable<IString, String> f) {
    List<FeatureValue<String>> features = Generics.newLinkedList();
    if (f.targetPhrase != null && f.targetPhrase.size() > 0) {
	    for (int i = 0; i < f.targetPhrase.size(); i++) {
		    String word = f.targetPhrase.get(i).toString();
		    if (preps.contains(word)) {
			    features.add(new FeatureValue<String>("PrepAt:"+i, 1.0));
		    } else if (word.equals(".") || word.equals(",")) {
			    features.add(new FeatureValue<String>("PunctAt:"+i, 1.0));
		    }
	    }
	    String lastWord = f.targetPhrase.get(f.targetPhrase.size() - 1).toString();
	    if (preps.contains(lastWord)) {
		    features.add(new FeatureValue<String>("EndsWithPrep", 1.0));
	    } else {
		    features.add(new FeatureValue<String>("DoesNotEndWithPrep:", 1.0));
	    }
	    if (lastWord.equals(".") || lastWord.equals(",")) {
		    features.add(new FeatureValue<String>("EndsWithPunct", 1.0));
	    } else {
		    features.add(new FeatureValue<String>("DoesNotEndWithPunct:", 1.0));
	    }
    }

    return features;
  }

  @Override
  public boolean isolationScoreOnly() {
    return false;
  }
}
