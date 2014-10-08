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
  
  private static final Set<String> punct = new HashSet<String>(Arrays.asList(",", "."));

  @Override
  public void initialize() {
    // Do any setup here.
  }

  @Override
  public List<FeatureValue<String>> ruleFeaturize(
      Featurizable<IString, String> f) {
    List<FeatureValue<String>> features = Generics.newLinkedList();
    Sequence<IString> target = f.targetPhrase;
    String lastWord = target.get(target.size() - 1).toString();
    if (punct.contains(lastWord)) {
      features.add(new FeatureValue<String>("EndsInPunct", 1.0));
    } else {
      features.add(new FeatureValue<String>("EndsInPunct", 0));
    }

    return features;
  }

  @Override
  public boolean isolationScoreOnly() {
    return false;
  }
}
