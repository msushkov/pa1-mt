package edu.stanford.nlp.mt.decoder.feat;

import java.util.List;
import java.lang.Math;

import edu.stanford.nlp.mt.util.FeatureValue;
import edu.stanford.nlp.mt.util.Featurizable;
import edu.stanford.nlp.mt.util.IString;
import edu.stanford.nlp.mt.decoder.feat.RuleFeaturizer;
import edu.stanford.nlp.util.Generics;

/**
 * A rule featurizer.
 */
public class DiffSrcDestLength implements RuleFeaturizer<IString, String> {
  
  private static final String FEATURE_NAME = "DIFF_FEAT";

  @Override
  public void initialize() {
    // Do any setup here.
  }

  @Override
  public List<FeatureValue<String>> ruleFeaturize(
      Featurizable<IString, String> f) {

    // TODO: Return a list of features for the rule. Replace these lines
    // with your own feature.
    List<FeatureValue<String>> features = Generics.newLinkedList();

    String newVal = String.format("%s:%d", FEATURE_NAME, Math.abs(f.targetPhrase.size() - f.sourcePhrase.size()));
    FeatureValue<String> newFeat = new FeatureValue<String>(newVal, 1.0);
    features.add(newFeat);
    
    return features;
  }

  @Override
  public boolean isolationScoreOnly() {
    return false;
  }
}
