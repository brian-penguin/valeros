(ns valeros.routes.home
  (:use clojure-csv.core)
  (:require [valeros.layout :as layout]
            [valeros.db.core :as db]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]))

(defn home-page []
  (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn about-page []
  (layout/render "about.html"))

(def spell-csv "docs/spells.csv")

(defn csv-data [file-location]
  "Get the spells from the csv file in a list. First row is the headers"
  (let [csv-file (slurp (io/resource file-location))]
  (parse-csv csv-file)))

;; This may be more accurate to a respoinse we might get outof a database
(defn csv-data->maps [csv-data]
  "Convert data to maps where the keys are the headers"
  (map zipmap
       (->> (first csv-data)
            (map keyword)
            repeat)
       (rest csv-data)))

(defn spell-page []
  "pass in the spell keys and spell-data for building a table"
  (let [spell-data (csv-data spell-csv)
        spell-keys (first spell-data)
        spells (rest spell-data)]
    (layout/render
     "spells.html" {:spell-keys spell-keys, :spells spell-data})))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/spells" [] (spell-page)))
