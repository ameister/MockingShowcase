package ch.bbv.mockingshowcase;

import ch.bbv.mockingshowcase.media.Media;

public interface Visitor{

  void visit(Media media);
}