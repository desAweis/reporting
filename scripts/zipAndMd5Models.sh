#!/bin/bash
createZip() {
  cd $1
  zip -R -q ../models.zip '*.m' '*.ema' '*.emam'
  ema="../models.zip"
  md5=`md5sum ${ema} | awk '{ print $1 }'`
  cd ..
  mv "models.zip" "models$md5.zip"
  return 0;
}