import React from 'react';
import {
  Button,
  Card
} from 'antd';
import {
  BarChart
} from 'react-d3-components';
import QueueAnim from 'rc-queue-anim';

export default class ArchivDetail extends React.Component {

  constructor(props) {
    super(props)
  }

  render() {
    var data = [{
      label: 'somethingA',
      values: [{
        x: 'SomethingA',
        y: 10
      }, {
        x: 'SomethingB',
        y: 4
      }, {
        x: 'SomethingC',
        y: 3
      }]
    }];

    return (
      <QueueAnim delay={10}>
        <div className='survey' key="survey">
          <Card title='每日新增用户' style={{ width: 440 }}>
            <BarChart
                  data={data}
                  width={380}
                  height={300}
                  margin={{top: 10, bottom: 50, left: 50, right: 10}}/>
          </Card>
          <Card title='每日新增用户' style={{ width: 440 }}>
            <BarChart
                  data={data}
                  width={380}
                  height={300}
                  margin={{top: 10, bottom: 50, left: 50, right: 10}}/>
          </Card>
          <Card title='每日新增用户' style={{ width: 440 }}>
            <BarChart
                  data={data}
                  width={380}
                  height={300}
                  margin={{top: 10, bottom: 50, left: 50, right: 10}}/>
          </Card>
          <Card title='每日新增用户' style={{ width: 440 }}>
            <BarChart
                  data={data}
                  width={380}
                  height={300}
                  margin={{top: 10, bottom: 50, left: 50, right: 10}}/>
          </Card>
        </div>
      </QueueAnim>
    );
  }
};