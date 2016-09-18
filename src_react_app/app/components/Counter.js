import React, {
  Component,
  PropTypes
} from 'react';

export default class Counter extends Component {

  componentWillMount() {
    console.log("componentWillMount")
  }

  componentDidMount() {
    console.log("componentDidMount")
  }
  componentWillUpdate() {
    console.log("componentWillUpdate")
  }

  componentDidUpdate() {
    console.log("componentDidUpdate")
  }

  componentWillReceiveProps() {
    console.log("componentWillReceiveProps")
  }

  componentWillUnmount() {
    console.log("componentWillUnmount")
  }

  render() {
    /*const {
      increment,
      incrementIfOdd,
      incrementAsync,
      decrement,
      counter
    } = this.props;*/
    return (
      <p>
        Clicked: {this.props.counter} times
        {' '}
        <button onClick={this.props.increment}>+</button>
        {' '}
        <button onClick={this.props.decrement}>-</button>
        {' '}
        <button onClick={this.props.incrementIfOdd}>Increment if odd</button>
        {' '}
        <button onClick={this.props.incrementAsync}>Increment async</button>
      </p>
    );
  }
}

Counter.propTypes = {
  increment: PropTypes.func.isRequired,
  incrementIfOdd: PropTypes.func.isRequired,
  incrementAsync: PropTypes.func.isRequired,
  decrement: PropTypes.func.isRequired,
  counter: PropTypes.number.isRequired
}