(ns purchases.core
  (:require [clojure.string :as str]
            [clojure.walk]))


(defn -main []
  (println "Enter a category (Furniture, Alcohol, Toiletries, Shoes, Food, Jewelry):")

  ;Read the file and parse it into a vector of lines
  (let [purchases (slurp "purchases.csv")

        ;Split each line into vectors (delimited by commas)
        purchases (str/split-lines purchases)
        ;Split each line into vectors (delimited by commas)
        purchases (map (fn [line] (str/split line #","))purchases)
        ;Separate the header from the rest of the lines
        headers (first purchases)
        purchases (rest purchases)
        ;Make each line a hash map that associates each item in the header with each item in the line
        purchases (map (fn [purchase]
                         (zipmap headers purchase))purchases)
        purchases (clojure.walk/keywordize-keys purchases)
        category (read-line)
        purchases (filter (fn [purchase] (= (get purchase :category) category))purchases)
        file-text (pr-str purchases)]
    (spit (str category ".edn") file-text)))

















