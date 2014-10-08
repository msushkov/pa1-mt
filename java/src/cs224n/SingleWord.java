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
public class SingleWord implements RuleFeaturizer<IString, String> {
 

  @Override
  public void initialize() {
    // Do any setup here.
  }

  @Override
  public List<FeatureValue<String>> ruleFeaturize(
      Featurizable<IString, String> f) {
    List<FeatureValue<String>> features = Generics.newLinkedList();
    /*if (f.sourcePhrase.size() == 1) {
      if (f.targetPhrase.size() == 1) {
        features.add(new FeatureValue<String>("SingleSource:SingleTarget", 1.0));
      } else {
        features.add(new FeatureValue<String>("SingleSource:MultiTarget", 1.0));
      }
    } else {
      if (f.targetPhrase.size() == 1) {
        features.add(new FeatureValue<String>("MultiSource:SingleTarget", 1.0));
      } else {
        features.add(new FeatureValue<String>("MultiSource:MultiTarget", 1.0));
      }
    }*/
    //features.add(new FeatureValue<String>("SourceLength:"+f.sourcePhrase.size()+"TargetLength:"+f.targetPhrase.size(), 1.0));
    features.add(new FeatureValue<String>("SourceLength:"+f.sourcePhrase.size(), 1.0));
    return features;
  }

  @Override
  public boolean isolationScoreOnly() {
    return false;
  }
}
