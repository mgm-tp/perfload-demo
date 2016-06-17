
// Host Configurations

hostConfigs {
	localhost {
		perfLoadHome = '..' // relative path
		osfamily = 'windows'
		daemonId = 1
		perfmon = true
		startup = [
			['dir': '..\\refapp', 'executable': '.\\bin\\refapp', 'args': ['install']],
			['dir': '..\\refapp', 'executable': '.\\bin\\refapp', 'args': ['start']]
		]
		shutdown = [
			['dir': '..\\refapp', 'executable': '.\\bin\\refapp', 'args': ['stop']],
			['dir': '..\\refapp', 'executable': '.\\bin\\refapp', 'args': ['remove']]
		]
		archiving {
			gcLogs {
				dir = '../refapp'
				files = 'gclog.txt'
				archiveName = 'gc-logs.zip'
				cleanup = false
			}
			wrapperLog {
				dir = '../refapp'
				files = 'wrapper.log'
				archiveName = 'wrapper-log.zip'
				cleanup = false
			}
			agentLogs {
				dir = '../agent'
				files = '*.log'
				archiveName = 'agent-logs.zip'
				cleanup = false
			}
		}
	}
}