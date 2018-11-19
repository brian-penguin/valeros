(ns valeros.routes.home
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

(defn memoize [f]
  (let [mem (atom {})]
    (fn [& args]
      (if-let [e (find @mem args)]
        (val e)
        (let [ret (apply f args)]
          (swap! mem assoc args ret)
          ret)))))

(defn spells []
  (-> "docs/spells.csv" io/resource slurp))

(def spells
  (memoize spells))

(defn spell-page []
  (layout/render
   "spells.html" {:spells (spells)}))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/spells" [] (spell-page)))
