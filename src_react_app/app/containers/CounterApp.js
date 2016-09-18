import React, {
  Component
} from 'react';
import {
  bindActionCreators
} from 'redux';
import {
  connect
} from 'react-redux';
import Counter from '../components/Counter';
import * as CounterActions from '../actions/CounterActions';

/*class CounterApp extends Component {
  render() {
    const {
      counter,
      dispatch
    } = this.props;
    return ( < Counter counter = {
        counter
      } {...bindActionCreators(CounterActions, dispatch)
      }
      />
    );
  }
}*/

function mapStateToProps(state) {
  return {
    counter: state.counter
  };
}

function mapDispatchToProps(dispatch) {
  return bindActionCreators(CounterActions, dispatch)
}

export default connect(mapStateToProps, CounterActions)(Counter);