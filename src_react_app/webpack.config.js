const path = require('path');
const webpack = require('webpack');
const autoprefixer = require('autoprefixer');
const HtmlwebpackPlugin = require('html-webpack-plugin');

const ROOT_PATH = path.resolve(__dirname);
const APP_PATH = path.resolve(ROOT_PATH, 'app');
const BUILD_PATH = path.resolve(ROOT_PATH, 'build');
const NODE_MODULES = path.resolve(__dirname, 'node_modules');

const config = {
	entry: {
		vds5: path.resolve(APP_PATH, 'index.js')
	},
	output: {
		path: BUILD_PATH,
		filename: '[name].bundle.js',
		publicPath: '/'
	},
	devtool: 'cheap-module-eval-source-map',
	devServer: {
		port: 1111,
		historyApiFallback: true,
		hot: true,
		inline: true,
		progress: true,
		proxy: {
			'/vds5s1/*': {
				target: 'http://localhost:8080/',
				host: 'example.com',
			}
		}
	},
	module: {
		loaders: [{
			test: /\.jsx?$/,
			loader: 'babel',
			include: APP_PATH
		}, {
			test: /\.css$/,
			loader: 'style!css!postcss'
		}, {
			test: /\.less$/,
			loader: 'style!css!less'
		}, {
			test: /\.scss$/,
			loader: 'style!css!sass'
		}, {
			test: /\.(png|jpg|jpeg|gif)$/,
			loader: 'url?limit=10000&name=assets/img/[name].[ext]'
		}, {
			test: /\.svg$/,
			loader: 'url?limit=65000&mimetype=image/svg+xml&name=assets/fonts/[name].[ext]'
		}, {
			test: /\.woff$/,
			loader: 'url?limit=65000&mimetype=application/font-woff&name=assets/fonts/[name].[ext]'
		}, {
			test: /\.woff2$/,
			loader: 'url?limit=65000&mimetype=application/font-woff2&name=assets/fonts/[name].[ext]'
		}, {
			test: /\.[ot]tf$/,
			loader: 'url?limit=65000&mimetype=application/octet-stream&name=assets/fonts/[name].[ext]'
		}, {
			test: /\.eot$/,
			loader: 'url?limit=65000&mimetype=application/vnd.ms-fontobject&name=assets/fonts/[name].[ext]'
		}, ]
	},
	postcss: [
		autoprefixer({
			browsers: ['last 3 versions', '> 1%']
		})
	],
	resolve: {
		alias: {
			'react': path.resolve(NODE_MODULES, 'react'),
		},
		extensions: ['', '.js', '.jsx']
	},
	plugins: [
		new webpack.optimize.UglifyJsPlugin({
			minimize: true
		}),
		new HtmlwebpackPlugin({
			favicon: './app/assets/img/favicon.ico',
			title: '健康档案系统',
			template: './app/templates/index.html',
			hash: 'true',
			filename: 'index.html',
			inject: 'body'
		})
	]
};

module.exports = config