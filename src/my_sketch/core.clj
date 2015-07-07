(ns my-sketch.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def screen-size 500)


(defn setup []
  (q/frame-rate 100)
  (q/color-mode :rgb)
  ;; initially the state is the empty map
  {:x 100 :y 150 :change-in-x 1})


(defn update-state [state]
  (println state)
  (if (> (:x state) 485)
    (assoc state
           :x (+ (:x state)
                 (:change-in-x state))
           :change-in-x -1)
    (assoc state
           :x (+ (:x state)
                 (:change-in-x state))
           :change-in-x (:change-in-x state))))

(defn draw-state [state]
  (q/color-mode :rgb)
  (q/background 100)
  (q/fill (q/color 100 250 200))
  (q/ellipse (:x state) (:y state) 60 80))


(q/defsketch my-sketch
  :size [screen-size screen-size]
  :setup setup

  :update update-state
  :draw draw-state

  :features [:keep-on-top]
  :middleware [m/fun-mode])
