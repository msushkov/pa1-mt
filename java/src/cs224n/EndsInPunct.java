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
public class EndsInPunct implements RuleFeaturizer<IString, String> {

  String lastWord;

  @Override
  public void initialize() {
    // Do any setup here.
  }

  @Override
  public List<FeatureValue<String>> ruleFeaturize(
      Featurizable<IString, String> f) {
    List<FeatureValue<String>> features = Generics.newLinkedList();
    if (f.targetPhrase != null && f.targetPhrase.size() > 0) {
      lastWord = f.targetPhrase.get(f.targetPhrase.size() - 1).toString();
      if (lastWord.equals(".") || lastWord.equals(",")) {
        features.add(new FeatureValue<String>("EndsInPunct", 1.0));
      } else {
        features.add(new FeatureValue<String>("DoesNotEndInPunct", 1.0));
      }
    }

    
    return features;
  }

  @Override
  public boolean isolationScoreOnly() {
    return false;
  }
}
