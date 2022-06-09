import Vue from 'vue'
import Vuex from 'vuex'
import song from './song'
import player from './player'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    song,
    player,
  }
})
