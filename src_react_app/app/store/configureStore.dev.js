import {
  createStore,
  applyMiddleware,
  compose
} from 'redux';
import {
  persistState
} from 'redux-devtools';
import thunk from 'redux-thunk';
import rootReducer from '../reducers';
import DevTools from '../containers/DevTools';

const enhancer = compose(
  applyMiddleware(thunk),
  DevTools.instrument(),
  persistState(
    window.location.href.match(
      /[?&]debug_session=([^&#]+)\b/
    )
  ),
  /*window.devToolsExtension ? window.devToolsExtension() : f => f*/
);

export default function configureStore(initialState) {
  const store = createStore(rootReducer, initialState, enhancer);

  if (module.hot) {
    module.hot.accept('../reducers', () =>
      store.replaceReducer(require('../reducers').default)
    );
  }

  return store;
}